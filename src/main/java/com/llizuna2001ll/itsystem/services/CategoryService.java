package com.llizuna2001ll.itsystem.services;

import com.llizuna2001ll.itsystem.entities.Category;
import com.llizuna2001ll.itsystem.exceptions.CategoryNotFoundException;
import com.llizuna2001ll.itsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByNameIgnoreCase(name).orElseThrow(CategoryNotFoundException::new);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
