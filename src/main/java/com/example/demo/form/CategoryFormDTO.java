package com.example.demo.form;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoryFormDTO {

    @NotNull(message = "Name can't be left blank")
    @Size(min = 3, message = "Name can't be less then 3 characters")
    private String name;
}