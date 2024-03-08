package com.example.calculator.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArithmeticRequestDto {
    @NotNull
    @NotEmpty
    private String expression;
}
