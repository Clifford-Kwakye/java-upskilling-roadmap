package com.example.calculator.arithmeticOperations.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArithmeticResponseDto {
    private String expression;
    private double result;
}
