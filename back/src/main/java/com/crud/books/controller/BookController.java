package com.crud.books.controller;


import com.crud.books.dto.BookDTO;
import com.crud.books.model.Book;
import com.crud.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
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
}
