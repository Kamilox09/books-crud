package com.crud.books.service;

import com.crud.books.model.Author;
import com.crud.books.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author save(Author author){
        return authorRepository.save(author);
    }

    public Author getAuthorIfExistsCreateIfNot(Author author){
        if(this.authorRepository.existsByFullName(author.getFullName())){
            return this.authorRepository.getByFullName(author.getFullName());
        }

        return this.save(author);
    }

    public Author getByFullName(String name){
        return this.authorRepository.getByFullName(name);
    }
}
