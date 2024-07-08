package com.example.demo.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
//@Document("ChargeRequest")
public class ChargeRequest {
    private String description;
    private int amount;
    private String stripeEmail;
    private String stripeToken;


}
