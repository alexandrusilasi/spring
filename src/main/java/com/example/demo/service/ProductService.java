package com.example.demo.service;

import com.example.demo.form.ProductFormDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

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

    public boolean createProduct(ProductFormDTO productFormDTO, MultipartFile imageFile){

        Product product = modelMapper.map(productFormDTO, Product.class);

        product.setImageUrl(FileService.upload(imageFile));

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
