package com.example.demo.product;


import com.example.demo.product.Product.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends ListPagingAndSortingRepository<Product, Long>, ListCrudRepository<Product, Long> {


    Page<Product> findAllByCategory(Category category, Pageable pageable);
    List<Product> findByCategory(Category category);
    Optional<Product> findById(Long id);

    @Query(value = "select * from product where LOWER(title) LIKE %?1% LIMIT 5", nativeQuery = true)
    List<Product> findProductsWithName(String name);

}
