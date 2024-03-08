package com.example.calculator.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArithmeticResponseDto {
    private String expression;
    private double result;
}
