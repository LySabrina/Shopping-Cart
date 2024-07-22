package com.example.demo.controllers;

import com.example.demo.dto.ProductDTO;
import com.example.demo.services.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckoutController {

    private final StripeService stripeService;

    public CheckoutController(StripeService stripeService){
        this.stripeService = stripeService;
    }

    @GetMapping("/testPay")
    public ResponseEntity<String> makeTestPayment() throws StripeException {
        String clientSecret = stripeService.makeTestPayment();
        return ResponseEntity.status(200).body("Your client secret: " + clientSecret);
    }

    /**
     * Create a payment checkout for user
     * Gets all the desired prodcuts and handle calculating the cost on the backend side
     * @param productDTOList List of products
     * @return client secret to be used for Stripe checkout
     */
    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> makePaymentIntent(@RequestBody List<ProductDTO> productDTOList){
        // Now that I got the produuct id and each amount, how should go about generating the Stripe checkout?
        // https://stackoverflow.com/questions/75710891/do-you-have-to-store-your-products-in-stripe-to-process-payment
        // don't have to fetch price from Stripe, no need to store products in  Stripe but would be good to sync up with Stripe for consistency


        //RequestBody fetch the Product Items to calculate the price of cart
        return null;
    }

}
