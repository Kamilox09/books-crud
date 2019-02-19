package com.crud.books.controller;


import com.crud.books.dto.CategoryDTO;
import com.crud.books.model.Category;
import com.crud.books.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(@RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "size", required = false) Integer size) {

        if (page == null || size == null) {
            List<Category> categories = this.categoryService.getAllCategories();
            return new ResponseEntity<List<CategoryDTO>>(categories.stream()
                    .map(x -> Mapper.mapToCategoryDTO(x))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        Pageable pageInfo = PageRequest.of(page - 1, size);
        List<Category> categories = this.categoryService.getPageOfCategories(pageInfo).getContent();
        return new ResponseEntity<List<CategoryDTO>>(categories.stream()
                .map(Mapper::mapToCategoryDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO dto) {
        Category category = Mapper.mapToCategoryEntity(dto);

        category = categoryService.save(category);
        if (category != null) {
            return new ResponseEntity<String>("Category created.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Category with given name already exists.", HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDTO dto) {
        Optional<Category> check = this.categoryService.getCategoryById(id);

        if (!check.isPresent()) {
            return new ResponseEntity<String>("Category you want to update does not exist", HttpStatus.BAD_REQUEST);
        }

        Category category = check.get();
        CategoryController.mergeCategory(dto, category);

        category = this.categoryService.save(category);
        if (category == null) {
            return new ResponseEntity<String>("Category with given name already exists.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>("Category updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        Optional<Category> check = this.categoryService.getCategoryById(id);

        if (!check.isPresent()) {
            return new ResponseEntity<String>("Category you want to delete does not exist", HttpStatus.BAD_REQUEST);
        }

        this.categoryService.deleteCategory(check.get());

        return new ResponseEntity<String>("Category deleted", HttpStatus.NO_CONTENT);
    }

    static private void mergeCategory(CategoryDTO dto, Category entity) {
        entity.setName(dto.getName());
    }
}
