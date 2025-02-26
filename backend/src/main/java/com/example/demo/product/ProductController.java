package com.example.demo.product;

import com.example.demo.product.Product.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    /**
     * Gets the products associated with a category
     * @param category category of the products
     * @param pageable Pageable, could also optionally do RequestParams
     * @return
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductInfoDTO>> getProductsFromCategory(@PathVariable String category, @PageableDefault(page = 0, size = 5) Pageable pageable){
        Category catEnum = Category.valueOf(category);
        Page<ProductInfoDTO> productInfoDTOS = productService.findAllByCategory(catEnum, pageable);
        return ResponseEntity.ok(productInfoDTOS);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductInfoDTO>> searchProduct(@RequestParam String name){
        List<ProductInfoDTO> products = productService.searchProduct(name);
        return ResponseEntity.ok(products);
    }


}
