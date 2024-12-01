package com.example.demo.controller;

import com.example.demo.form.ProductFormDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/products")
    public String getProducts(Model model)
    {
        List<Product> products = productService.getProducts();

        model.addAttribute("products" , products);

        return "product/index";
    }

//    @PostMapping("/product")
//    @ResponseBody
//    public void addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile, RedirectAttributes redirectAttributes)
//    {
//        productService.addProduct(product);
//    }

    @GetMapping("/product/add")
    public String addProduct(Model model)
    {
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories" , categories);

        return "product/add";
    }

    @PostMapping("/product/create")
    public RedirectView createProduct(@Valid @ModelAttribute ProductFormDTO productFormDTO, @RequestPart MultipartFile imageFile, RedirectAttributes redirectAttributes) throws IOException {

        boolean isSaved = productService.createProduct(productFormDTO, imageFile);

        if(isSaved)
        {
            redirectAttributes.addFlashAttribute("message", "Product added successfully");
        }
        else
        {
            redirectAttributes.addFlashAttribute("message", "Product already exists");
        }

        return new RedirectView("/products");
    }

    @GetMapping("/product/{prodSlug}")
    public String getProduct(@PathVariable String prodSlug, Model model)
    {
        Product product = productService.getProductBySlug(prodSlug);

        model.addAttribute("product" , product);

        return "product/view";
    }

    @PutMapping("/product")
    public void updateProduct(@RequestBody Product product)
    {
        productService.updateProduct(product);
    }

    @DeleteMapping("/product")
    public void deleteProduct(@RequestBody Product product)
    {
        productService.deleteProduct(product);
    }

}
