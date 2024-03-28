package com.example.calculator.service.arithmeticOperations;

import com.example.calculator.dto.request.ArithmeticRequestDto;
import com.example.calculator.dto.response.ArithmeticResponseDto;
import com.example.calculator.service.utils.Stack;
import com.example.calculator.service.validations.GenericValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class ArithmeticOperations implements Arithmetics {
  private final GenericValidation<Object> genericValidation;
  @Override
  public ArithmeticResponseDto add(ArithmeticRequestDto arithmeticRequestDto)
      throws ArithmeticException {
    genericValidation.checkViolations(arithmeticRequestDto);

    double[] convertedOperands = convertNumbersInExpressionToDoubles(arithmeticRequestDto, "+");
    double result = 0;

    for (double convertedOperand : convertedOperands) {
      result += convertedOperand;
    }

    return ArithmeticResponseDto.builder()
        .expression(arithmeticRequestDto.getExpression())
        .result(result)
        .build();
  }

  @Override
  public ArithmeticResponseDto subtract(ArithmeticRequestDto arithmeticRequestDto) throws ArithmeticException {
    genericValidation.checkViolations(arithmeticRequestDto);
    double[] convertedOperands = convertNumbersInExpressionToDoubles(arithmeticRequestDto, "-");

    double result = 0;
    for (double convertedOperand : convertedOperands) {
      result -= convertedOperand;
    }

    return ArithmeticResponseDto.builder()
        .expression(arithmeticRequestDto.getExpression())
        .result(result)
        .build();
  }

  @Override
  public ArithmeticResponseDto divide(ArithmeticRequestDto arithmeticRequestDto) throws ArithmeticException {
    genericValidation.checkViolations(arithmeticRequestDto);
    double[] convertedOperands = convertNumbersInExpressionToDoubles(arithmeticRequestDto, "/");

    double result = convertedOperands[0] / convertedOperands[1];
    return ArithmeticResponseDto.builder()
        .expression(arithmeticRequestDto.getExpression())
        .result(result)
        .build();
  }

  private double[] convertNumbersInExpressionToDoubles(
      ArithmeticRequestDto arithmeticRequestDto, String operator) {
    genericValidation.checkViolations(arithmeticRequestDto);

    String expression = arithmeticRequestDto.getExpression().trim();
    String regex = Pattern.quote(operator);
    String[] operands = expression.split(regex);
    double[] convertedOperands = new double[operands.length];

    for (int i = 0; i < operands.length; i++) {
      convertedOperands[i] = Double.parseDouble(operands[i]);
    }

    return convertedOperands;
  }
}
