package com.example.demo.controller;

import com.example.demo.form.CategoryFormDTO;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public String getCategories(Model model)
    {
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories" , categories);

        return "category/index";
    }

    @PostMapping("/category")
    @ResponseBody
    public void addCategory(@RequestBody Category category)
    {
        categoryService.addCategory(category);
    }

    @GetMapping("/category/add")
    public String addCategory(Model model)
    {
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories" , categories);

        return "category/add";
    }

    @PostMapping("/category/create")
    public RedirectView createCategory(@Valid @ModelAttribute CategoryFormDTO categoryFormDTO, RedirectAttributes redirectAttributes)
    {
        boolean isSaved = categoryService.createCategory(categoryFormDTO);

        if(isSaved)
        {
            redirectAttributes.addFlashAttribute("message", "Category added successfully");
        }
        else
        {
            redirectAttributes.addFlashAttribute("message", "Category already exists");
        }

        return new RedirectView("/categories");
    }

    @GetMapping("/category/{categorySlug}")
    public String getProduct(@PathVariable String categorySlug, Model model)
    {
        Category category = categoryService.getCategoryBySlug(categorySlug);

        model.addAttribute("category" , category);

        return "category/view";
    }

    @PutMapping("/category")
    public void updateCategory(@RequestBody Category category)
    {
        categoryService.updateCategory(category);
    }

    @DeleteMapping("/category")
    public void deleteCategory(@RequestBody Category category)
    {
        categoryService.deleteCategory(category);
    }

}
