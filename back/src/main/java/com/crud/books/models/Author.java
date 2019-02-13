package com.crud.books.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String fullName;

    @OneToMany(mappedBy = "author")
    private List<Book> booksOfAuthor;


}
