package com.example.demo.wishlist;

import com.example.demo.product.ProductInfoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @GetMapping("/")
    public List<ProductInfoDTO> getWishlist(@RequestBody String email){
        return null;
    }
}
