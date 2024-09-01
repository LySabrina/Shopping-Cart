package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Product")
public class Product extends Base{

    private String title;
    private long price;
    private Category category;
    private String description;
    private String image;

    public enum Category{
        ELECTRONICS,
        JEWELERY,
        MEN,
        WOMEN
    }
}
