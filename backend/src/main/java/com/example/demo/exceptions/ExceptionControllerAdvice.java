package com.example.demo.exceptions;

import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionController that catches exceptions thrown from @Controller methods
 * This is a global exception class to handle common exception that may occur in different Controller classes
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> handleStripeException(StripeException stripeException){
        System.out.println(stripeException.getMessage() + " at " + stripeException.getCode());
        return ResponseEntity.status(501).body("Failed to make Stripe PaymentIntent " + stripeException.getMessage() + " At code: " + stripeException.getCode());
    }
}
