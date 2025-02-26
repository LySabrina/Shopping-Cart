package com.example.demo.product;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.Product.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(String id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(Long.parseLong(id));
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public Page<ProductInfoDTO> findAllByCategory(Category catEnum , Pageable pageable){
        Page<Product> productPage = productRepository.findAllByCategory(catEnum, pageable);
        return productPage.map((item)-> new ProductInfoDTO(item.getTitle(),
                item.getPrice(), item.getCategory(), item.getDescription(), item.getImage()));
    }

    public List<ProductInfoDTO> searchProduct(String name){
        List<Product> findProducts = productRepository.findProductsWithName(name);
        return findProducts.stream().map((item)->
                new ProductInfoDTO(item.getTitle(), item.getPrice(), item.getCategory(),
                        item.getDescription(),
                        item.getImage())).toList();
    }

    //https://stackoverflow.com/questions/175532/should-a-retrieval-method-return-null-or-throw-an-exception-when-it-cant-prod
//    https://www.pablogonzalez.io/should-apex-methods-ever-return-null/
    //https://medium.com/@aashigangrade06/null-object-pattern-0300e7386ada
}
