package com.example.demo.product;

import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Online, it seems if we expect our result to return null, we can return null.
 * If its an abnormal condition then we return an exception
 * <p>
 * Although, I do not want to deal with returning nulls as thats error-prone.
 * The other alternative is to return Exception (but in this case, IM returning a bunch of exception and creaating different exceptions
 * There is also the alternative to use a Null Object Pattern (TODO to look up)
 * But for now in my implementation, I will be using exceptions
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(String id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    //https://stackoverflow.com/questions/175532/should-a-retrieval-method-return-null-or-throw-an-exception-when-it-cant-prod
//    https://www.pablogonzalez.io/should-apex-methods-ever-return-null/
    //https://medium.com/@aashigangrade06/null-object-pattern-0300e7386ada
}
