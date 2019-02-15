package com.crud.books.model;


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Book> getBooksOfAuthor() {
        return booksOfAuthor;
    }

    public void setBooksOfAuthor(List<Book> booksOfAuthor) {
        this.booksOfAuthor = booksOfAuthor;
    }
}
