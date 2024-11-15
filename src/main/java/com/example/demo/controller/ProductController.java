package com.example.demo.controller;

import com.example.demo.form.ProductFormDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public String getProducts(Model model)
    {
        List<Product> products = productService.getProducts();

        model.addAttribute("products" , products);

        return "product/index";
    }

    @PostMapping("/product")
    @ResponseBody
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    @GetMapping("/product/add")
    public String addProduct()
    {
        return "product/add";
    }

    @PostMapping("/product/create")
    public RedirectView createProduct(@Valid @ModelAttribute ProductFormDTO productFormDTO, RedirectAttributes redirectAttributes)
    {
        System.out.println(productFormDTO);

        boolean isSaved = productService.createProduct(productFormDTO);

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
