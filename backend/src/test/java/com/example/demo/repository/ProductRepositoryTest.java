//package com.example.demo.repository;
//
//import com.example.demo.product.Product;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataMongoTest
//class ProductRepositoryTest {
//    @Autowired
//    ProductRepository productRepository;
//
//    @Test
//    void findByCategory() {
//        //given
//        List<Product> productList = productRepository.findByCategory("MEN");
//
//        //when
//        //that
//        assertThat(productList).isEmpty();
//
//    }
//
//    @Test
//    void findById() {
//    }
//}