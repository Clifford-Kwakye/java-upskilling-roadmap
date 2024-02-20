package com.example.calculator.arithmeticOperations.service;

import com.example.calculator.arithmeticOperations.dto.request.ArithmeticRequestDto;
import com.example.calculator.arithmeticOperations.dto.response.ArithmeticResponseDto;

public interface Arithmetics {
    ArithmeticResponseDto add(ArithmeticRequestDto additionRequestDto);
}
