package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String msg;
    private List<Map<String, String>> errors;

}
