package com.crud.books.controller;

import com.crud.books.dto.BookDTO;
import com.crud.books.dto.CategoryDTO;
import com.crud.books.model.Author;
import com.crud.books.model.Book;
import com.crud.books.model.Category;

public class Mapper {

    static BookDTO mapToBookDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setNumberOfPages(book.getNumberOfPages());
        dto.setAuthorName(book.getAuthor().getFullName());
        dto.setCategoryName(book.getCategory().getName());
        return dto;
    }

    static CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    static Category mapToCategoryEntity(CategoryDTO dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    static Book mapToBookEntity(BookDTO dto){
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setNumberOfPages(dto.getNumberOfPages());

        Author author = new Author();
        author.setId(null);
        author.setFullName(dto.getAuthorName());

        Category category = new Category();
        category.setId(null);
        category.setName(dto.getCategoryName());

        entity.setAuthor(author);
        entity.setCategory(category);
        return entity;
    }


}
