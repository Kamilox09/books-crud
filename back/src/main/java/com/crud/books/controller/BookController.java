package com.crud.books.controller;


import com.crud.books.dto.BookDTO;
import com.crud.books.model.Author;
import com.crud.books.model.Book;
import com.crud.books.model.Category;
import com.crud.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<BookDTO>> getBooks(@RequestParam("page") Integer page,
                                                  @RequestParam("size") Integer size,
                                                  @RequestParam(value = "category", required = false) String category,
                                                  @RequestParam(value = "author", required = false) String author){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Book> books;
        if(category != null && author != null) {
            books = this.bookService.getBooksByAuthorNameAndCategoryName(category, author, pageable);
        } else if(category == null && author != null) {
            books = this.bookService.getBooksByAuthorName(author, pageable);
        } else if (category != null && author == null) {
            books = this.bookService.getBooksByCategoryName(category, pageable);
        } else {
            books = this.bookService.getPageOfBooks(pageable);
        }
        return new ResponseEntity<Page<BookDTO>>(books
                .map(Mapper::mapToBookDTO),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO dto){
        Book book = Mapper.mapToBookEntity(dto);
        JSONObject json = new JSONObject();

        book = bookService.save(book);

        if(book!=null){
            json.put("message", "Book created");
            return new ResponseEntity<String>(json.toJSONString(), HttpStatus.OK);
        }
            json.put("message","Book with given title already exists.");
        return new ResponseEntity<String>(json.toJSONString(), HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Integer id, @RequestBody BookDTO dto){
        Optional<Book> check = this.bookService.getBookById(id);
        JSONObject json = new JSONObject();

        if(!check.isPresent()){
            json.put("message", "Book you want to update does not exist");
            return new ResponseEntity<String>(json.toJSONString(), HttpStatus.BAD_REQUEST);
        }

        Book book = check.get();
        BookController.merge(dto,book);

        this.bookService.edit(book);

        json.put("message", "Book updated");
        return new ResponseEntity<String>(json.toJSONString(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        Optional<Book> check = this.bookService.getBookById(id);
        JSONObject json = new JSONObject();

        if(!check.isPresent()){
            json.put("message","Book you want to delete does not exist");
            return new ResponseEntity<String>(json.toJSONString(), HttpStatus.BAD_REQUEST);
        }

        this.bookService.deleteBook(check.get());
        json.put("message", "Book deleted");
        return new ResponseEntity<String>(json.toJSONString(), HttpStatus.NO_CONTENT);

    }

    static private void merge(BookDTO dto, Book entity){
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setNumberOfPages(dto.getNumberOfPages());
        Category category = entity.getCategory();
        category.setName(dto.getCategoryName());
        entity.setCategory(category);
        Author author = entity.getAuthor();
        if(!author.getFullName().equals(dto.getAuthorName())) {
            Author newAuthor = new Author();
            newAuthor.setFullName(dto.getAuthorName());
            entity.setAuthor(newAuthor);
        }
    }
}
