package com.crud.books.service;


import com.crud.books.model.Author;
import com.crud.books.model.Book;
import com.crud.books.model.Category;
import com.crud.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public Page<Book> getPageOfBooks(Pageable pageable){
        return this.bookRepository.findAll(pageable);
    }

    public Book save(Book book) {
        if (this.bookRepository.existsByTitle(book.getTitle())) {
            return null;
        }
        book.setCategory(this.categoryService.getCategoryByName(book.getCategory().getName()));
        book.setAuthor(this.authorService.getAuthorIfExistsCreateIfNot(book.getAuthor()));
        return this.bookRepository.save(book);
    }

    public void edit(Book book){
        book.setCategory(this.categoryService.getCategoryByName(book.getCategory().getName()));
        book.setAuthor(this.authorService.getAuthorIfExistsCreateIfNot(book.getAuthor()));
        this.bookRepository.save(book);
    }

    public Optional<Book> getBookById(Integer id){
        return this.bookRepository.findById(id);
    }

    public void deleteBook(Book book){
        this.bookRepository.delete(book);
    }

    public Page<Book> getBooksByCategoryName(String name, Pageable pageable){
        Category category = this.categoryService.getCategoryByName(name);
        if(category!=null)
            return this.bookRepository.getBooksByCategoryId(category.getId(), pageable);
        return Page.empty();
    }

    public Page<Book> getBooksByAuthorName(String name, Pageable pageable){
        Author author = this.authorService.getByFullName(name);
        if (author!=null)
            return this.bookRepository.getBooksByAuthorId(author.getId(), pageable);
        return Page.empty();
    }

    public Page<Book> getBooksByAuthorNameAndCategoryName(String categoryName, String authorName, Pageable pageable){
        Category category = this.categoryService.getCategoryByName(categoryName);
        Author author = this.authorService.getByFullName(authorName);
        if(category == null || author == null){
            return Page.empty();
        }

        return this.bookRepository.getBooksByAuthorIdAndCategoryId(author.getId(), category.getId(), pageable);

    }
}
