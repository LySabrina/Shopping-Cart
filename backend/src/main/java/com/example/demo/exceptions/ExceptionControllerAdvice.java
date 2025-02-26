package com.example.demo.exceptions;

import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ExceptionController that catches exceptions thrown from @Controller methods
 * This is a global exception class to handle common exception that may occur in different Controller classes
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> handleStripeException(StripeException stripeException) {
        System.out.println(stripeException.getMessage() + " at " + stripeException.getCode());
        return ResponseEntity.status(500).body("Failed to make Stripe PaymentIntent " + stripeException.getMessage() + " At code: " + stripeException.getCode());
    }

    /**
     * Handles the exception related to Java Annotation Validations
     *
     * @param validException exception thrown by any validation annotation
     * @return ResponseEntity with ErrorDTO
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(MethodArgumentNotValidException validException) {
        List<FieldError> fieldErrors = validException.getFieldErrors();

        List<Map<String, String>> errors = fieldErrors.stream().map(field -> {
            Map<String, String> error = new HashMap<>();
            error.put(field.getField(), field.getDefaultMessage());
            return error;
        }).toList();

        ErrorDTO errorDTO = new ErrorDTO("Invalid Registration", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productNotFoundException.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Credentials");
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid id");
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User: " + exception.getMessage() + " already exist");
    }
}
