package com.crud.books.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> booksInCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksInCategory() {
        return booksInCategory;
    }

    public void setBooksInCategory(List<Book> booksInCategory) {
        this.booksInCategory = booksInCategory;
    }
}
