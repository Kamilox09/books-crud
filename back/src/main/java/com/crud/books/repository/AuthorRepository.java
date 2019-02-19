package com.crud.books.repository;

import com.crud.books.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, JpaSpecificationExecutor<Author> {
    boolean existsByFullName(String fullName);
    Author getByFullName(String fullName);
}
