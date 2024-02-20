package com.example.calculator.arithmeticOperations.service;

import com.example.calculator.arithmeticOperations.dto.request.ArithmeticRequestDto;
import com.example.calculator.arithmeticOperations.dto.response.ArithmeticResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArithmeticOperations implements Arithmetics {
  @Override
  public ArithmeticResponseDto add(ArithmeticRequestDto additionRequestDto) {
    String expression = additionRequestDto.getExpression().trim();
    String[] operands = expression.split("\\+");
    double[] convertedOperands = new double[operands.length];

    for (int i = 0; i < operands.length; i++) {
      convertedOperands[i] = Double.parseDouble(operands[i]);
    }

    double result = 0;

      for (double convertedOperand : convertedOperands) {
          result += convertedOperand;
      }

    return ArithmeticResponseDto.builder()
        .expression(expression)
        .result(result)
        .build();
  }
}
