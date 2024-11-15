package com.example.demo.form;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFormDTO {

    @NotNull
    private Boolean available;

    @NotNull(message = "Name can't be left blank")
    @Size(min = 3, message = "Name can't be less then 3 characters")
    private String name;

    @NotNull(message = "Description can't be left blank")
    @Size(min = 3, message = "Description can't be less then 3 characters")
    private String description;

    @DecimalMin(value = "0.1")
    private BigDecimal price;

    @Min(value = 1, message = "Minimum quantity is 1")
    @NotNull(message = "Quantity can't be left bank")
    private Integer quantity;

    @NotNull(message = "Category can't be left blank")
    @Size(min = 3, message = "Category can't be less then 3 characters")
    private String category;


    @NotNull(message = "Brand can't be left blank")
    @Size(min = 3, message = "Category can't be less then 3 characters")
    private String brand;

    public ProductFormDTO() {
        this.available = true;
    }
}
