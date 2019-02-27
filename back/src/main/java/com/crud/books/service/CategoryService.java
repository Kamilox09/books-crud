package com.crud.books.service;


import com.crud.books.model.Category;
import com.crud.books.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> getAllCategories(){
        return new PageImpl<Category>(this.categoryRepository.findAll());
    }

    public Category save(Category category){
        if(categoryRepository.existsByName(category.getName()))
            return null;
        return this.categoryRepository.save(category);
    }

    public void edit(Category category){
        this.categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Integer id){
        return this.categoryRepository.findById(id);
    }

    public void deleteCategory(Category category){
        this.categoryRepository.delete(category);
    }

    public Page<Category> getPageOfCategories(Pageable pageable){
        return this.categoryRepository.findAll(pageable);
    }

    public Category getCategoryByName(String name){
        return this.categoryRepository.getByName(name);
    }


}
