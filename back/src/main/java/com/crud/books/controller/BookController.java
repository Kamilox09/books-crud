package com.crud.books.controller;


import com.crud.books.dto.BookDTO;
import com.crud.books.model.Author;
import com.crud.books.model.Book;
import com.crud.books.model.Category;
import com.crud.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        Pageable pageable = PageRequest.of(page-1, size);

        List<Book> books = this.bookService.getPageOfBooks(pageable).getContent();
        return new ResponseEntity<List<BookDTO>>(books.stream()
                .map(Mapper::mapToBookDTO)
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO dto){
        Book book = Mapper.mapToBookEntity(dto);

        book = bookService.save(book);

        if(book!=null){
            return new ResponseEntity<String>("Book created", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Book with given title already exists.", HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Integer id, @RequestBody BookDTO dto){
        Optional<Book> check = this.bookService.getBookById(id);

        if(!check.isPresent()){
            return new ResponseEntity<String>("Book you want to update does not exist", HttpStatus.BAD_REQUEST);
        }

        Book book = check.get();
        BookController.merge(dto,book);

        book = this.bookService.save(book);
        if(book == null){
            return new ResponseEntity<String>("Book with given title already exists", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>("Book updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        Optional<Book> check = this.bookService.getBookById(id);

        if(!check.isPresent()){
            return new ResponseEntity<String>("Book you want to delete does not exist", HttpStatus.BAD_REQUEST);
        }

        this.bookService.deleteBook(check.get());

        return new ResponseEntity<String>("Book deleted", HttpStatus.NO_CONTENT);

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
