package com.crud.books.models;


import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private Integer numberOfPages;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Category category;


}
