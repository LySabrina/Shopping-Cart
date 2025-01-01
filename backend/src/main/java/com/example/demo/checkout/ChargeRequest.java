package com.example.demo.checkout;

import lombok.Data;

@Data
//@Document("ChargeRequest")
public class ChargeRequest {
    private String description;
    private int amount;
    private String stripeEmail;
    private String stripeToken;

}
