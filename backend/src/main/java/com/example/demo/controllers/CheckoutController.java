package com.example.demo.controllers;

import com.example.demo.services.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {

    private final StripeService stripeService;

    public CheckoutController(StripeService stripeService){
        this.stripeService = stripeService;
    }

    @GetMapping("/testPay")
    public ResponseEntity<String> makeTestPayment(){
        try{
            String clientSecret = stripeService.makeTestPayment();
            return ResponseEntity.status(200).body("Your client secret: " + clientSecret);
        }
        catch (StripeException e){

            return ResponseEntity.status(500).body("Failed to make Stripe PaymentIntent " + e.getMessage() + " At code: " + e.getCode());
        }
    }

}
