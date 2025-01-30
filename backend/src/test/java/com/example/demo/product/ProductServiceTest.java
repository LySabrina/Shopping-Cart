package com.example.demo.product;

import com.example.demo.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testFindByIdSuccess() throws ProductNotFoundException {

        // arrange
        String id = "5099803df3f4948bd2f98391";
        Product product = new Product(id, "Title", 21L, Product.Category.ELECTRONICS,
                "Description",
                "image"
        );
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

        //act
        Product p = productService.findById(id);

        // assert
        Mockito.verify(productRepository).findById(id);
        assertThat(p).isEqualTo(product);
        Mockito.verifyNoMoreInteractions(productRepository);

    }

    @Test
    void findByIdFail() throws ProductNotFoundException {
        // arrange
        String id = "5099803df3f4948bd2f98391";
        Product product = new Product(id, "Title", 21L, Product.Category.ELECTRONICS,
                "Description",
                "image"
        );
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

        // act + assert
        assertThatExceptionOfType(ProductNotFoundException.class).isThrownBy(() -> {
            productService.findById(id);
        });
        Mockito.verifyNoMoreInteractions(productRepository);
    }

    @Test
    void findByCategory() {
        // arrange
        String category = "MEN";
        List<Product> productList = List.of(
                new Product("title", 21L, Product.Category.MEN, "description", "image")
        );
        Mockito.when(productRepository.findByCategory(category)).thenReturn(productList);

        //act
        List<Product> list = productService.findByCategory(category);

        // assert
        assertThat(list).isEqualTo(productList);
        Mockito.verifyNoMoreInteractions(productRepository);
    }

    @Test
    void findByCategoryFail() {
        // arrange
        String category = "TOYS";
        Mockito.when(productRepository.findByCategory(category)).thenReturn(List.of());

        // act
        List<Product> list = productService.findByCategory(category);

        //assert
        assertThat(list).isEmpty();
        Mockito.verifyNoMoreInteractions(productRepository);

    }
}
