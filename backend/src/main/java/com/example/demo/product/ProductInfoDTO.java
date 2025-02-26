package com.example.demo.product;

import com.example.demo.product.Product.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductInfoDTO {
    private String title;
    private long price;
    private Category category;
    private String description;
    private String image;
}
