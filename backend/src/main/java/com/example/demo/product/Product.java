package com.example.demo.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Product")
public class Product {
    @Id
    private String id;
    private String title;
    private long price;
    private Category category;
    private String description;
    private String image;

    public Product(String title, long price, Category category, String description, String image) {
    }

    public enum Category{
        ELECTRONICS,
        JEWELERY,
        MEN,
        WOMEN
    }


}
