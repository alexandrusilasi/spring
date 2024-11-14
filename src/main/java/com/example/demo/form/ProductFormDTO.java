package com.example.demo.form;

import lombok.Data;

@Data
public class ProductFormDTO {
    private boolean available;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String brand;
}
