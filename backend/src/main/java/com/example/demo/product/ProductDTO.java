package com.example.demo.product;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private long amount;

}
