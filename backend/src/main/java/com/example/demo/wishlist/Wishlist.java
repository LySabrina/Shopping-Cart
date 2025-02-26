package com.example.demo.wishlist;

import com.example.demo.product.Product;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
