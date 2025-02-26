package com.example.demo.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDTO {
    @NotBlank
    private String text;

    @Min(value = 1)
    @Max(value = 5)
    private Integer rating;

    @NotBlank
    private String email;
}
