package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
