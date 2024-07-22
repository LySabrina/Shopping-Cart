package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class ProductDTO {
    private ObjectId id;
    private int amount;

}
