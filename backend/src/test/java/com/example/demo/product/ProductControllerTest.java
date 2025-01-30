package com.example.demo.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProductsFromCategory() throws Exception {
        // arrange
        String category = "MEN";
        List<Product> productList = List.of(
                new Product("title", 21L, Product.Category.MEN, "description", "image")
        );
        when(productService.findByCategory(category)).thenReturn(productList);

        //act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/category/{category}", category)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

        ;


        // assert
    }
}
