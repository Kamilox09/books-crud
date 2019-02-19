package com.crud.books.repository;

import com.crud.books.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    boolean existsByTitle(String title);

    @Query(value="select * from Book b where b.category_id=:id", nativeQuery = true)
    List<Book> getBooksByCategoryId(@Param("id") Integer id, Pageable pageable);

    @Query(value = "select * from Book b where b.author_id=:id", nativeQuery = true)
    List<Book> getBooksByAuthorId(@Param("id") Integer id, Pageable pageable);

    @Query(value="select * from Book b where b.category_id=:category_id and b.author_id=:author_id", nativeQuery = true)
    List<Book> getBooksByAuthorIdAndCategoryId(@Param("author_id") Integer author_id,
                                               @Param("category_id") Integer category_id,
                                               Pageable pageable);

}