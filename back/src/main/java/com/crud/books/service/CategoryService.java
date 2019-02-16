package com.crud.books.service;


import com.crud.books.dto.CategoryDTO;
import com.crud.books.model.Category;
import com.crud.books.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Category> getAllCategories(){
        return this.categoryRepository.findAll();
    }

    public Category save(Category category){
        if(categoryRepository.existsByName(category.getName()))
            return null;
        return this.categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Integer id){
        return this.categoryRepository.findById(id);
    }

    public void deleteCategory(Category category){
        this.categoryRepository.delete(category);
    }


}
