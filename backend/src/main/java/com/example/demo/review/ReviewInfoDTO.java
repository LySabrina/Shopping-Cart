package com.example.demo.review;

import com.example.demo.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewInfoDTO {
    private String text;
    private LocalDateTime creationDate;
    private User user;
}
