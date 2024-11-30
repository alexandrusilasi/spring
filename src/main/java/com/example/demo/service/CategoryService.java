package com.example.demo.service;

import com.example.demo.form.CategoryFormDTO;
import com.example.demo.form.ProductFormDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id).orElse(new Category());
    }
    public Category getCategoryBySlug(String slug) {
        return categoryRepo.findBySlug(slug);
    }

    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    public boolean createCategory(CategoryFormDTO categoryFormDTO) {
        Category category = new Category();

        category.setName(categoryFormDTO.getName());

        categoryRepo.save(category);

        return true;
    }

    public void updateCategory(Category category) {
        categoryRepo.save(category);
    }

    public void deleteCategory(Category category) {
        categoryRepo.delete(category);
    }
}
