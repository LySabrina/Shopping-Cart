package com.example.demo.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
@CrossOrigin("http://localhost:5173")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsFromCategory(@PathVariable String category){
        List<Product> products = productService.findByCategory(category);
        return ResponseEntity.status(200).body(products);
    }

}
