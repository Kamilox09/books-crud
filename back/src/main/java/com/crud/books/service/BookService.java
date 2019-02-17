package com.crud.books.service;


import com.crud.books.model.Book;
import com.crud.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository,
                       CategoryService categoryService,
                       AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    public Book save(Book book) {
        if (this.bookRepository.existsByTitle(book.getTitle())) {
            return null;
        }
        book.setCategory(this.categoryService.getCategoryByName(book.getCategory().getName()));
        book.setAuthor(this.authorService.getAuthorIfExistsCreateIfNot(book.getAuthor()));
        return this.bookRepository.save(book);
    }
}
