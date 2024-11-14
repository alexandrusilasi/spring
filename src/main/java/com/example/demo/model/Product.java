package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

@Component
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(unique = true)
    private String slug;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date releaseDate;
    private boolean available;
    private int quantity;

    @Override
    public String toString() {

        return "Name: '" + this.name + "', Price: '" + this.price + "', Category: '" + this.category + "', Brand: '" + this.brand + "', " + "Price: '" + this.price + "', Quantity: '" + this.quantity + "', " + "Available: '" + this.available + "'";
    }

    @PrePersist
    @PreUpdate
    public void setSlug()
    {
        this.slug = generateSlug(this.name);
    }

    private String generateSlug(String title) {
        return title.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-");
    }
}
