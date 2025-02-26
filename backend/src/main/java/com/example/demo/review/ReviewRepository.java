package com.example.demo.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends ListPagingAndSortingRepository<Review, Long>, ListCrudRepository<Review, Long> {

    Page<Review> findAllById(Long id, Pageable pageable);

}
