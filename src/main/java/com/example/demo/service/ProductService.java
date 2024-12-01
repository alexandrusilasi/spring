package com.example.demo.service;

import com.example.demo.form.ProductFormDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(new Product());
    }
    public Product getProductBySlug(String slug) {
        return productRepo.findBySlug(slug);
    }

    public void addProduct(Product product){
        productRepo.save(product);
    }

    public boolean createProduct(ProductFormDTO productFormDTO, MultipartFile imageFile) throws IOException {
        Product product = new Product();

        product.setName(productFormDTO.getName());
        product.setDescription(productFormDTO.getDescription());
        product.setPrice(productFormDTO.getPrice());
        product.setCategories(productFormDTO.getCategories());
        product.setBrand(productFormDTO.getBrand());
        product.setAvailable(productFormDTO.getAvailable());
        product.setSlug(generateUniqueSlug(productFormDTO.getName()));

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());

        productRepo.save(product);

        return true;
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(Product product) {
        productRepo.delete(product);
    }

    public String generateUniqueSlug(String title) {
        String baseSlug = title.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-");

        String slug = baseSlug;
        int counter = 1;

        while (productRepo.existsBySlug(slug)) {
            slug = baseSlug + "-" + counter++;
        }

        return slug;
    }
}
