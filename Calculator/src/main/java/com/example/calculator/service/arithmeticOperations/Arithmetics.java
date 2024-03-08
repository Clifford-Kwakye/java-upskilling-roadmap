package com.example.calculator.service.arithmeticOperations;

import com.example.calculator.dto.request.ArithmeticRequestDto;
import com.example.calculator.dto.response.ArithmeticResponseDto;

public interface Arithmetics {
    ArithmeticResponseDto add(ArithmeticRequestDto arithmeticRequestDto);
    ArithmeticResponseDto subtract(ArithmeticRequestDto arithmeticRequestDto);

    ArithmeticResponseDto divide(ArithmeticRequestDto arithmeticRequestDto);
}
