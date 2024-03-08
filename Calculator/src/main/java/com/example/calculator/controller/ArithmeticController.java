package com.example.calculator.controller;

import com.example.calculator.dto.request.ArithmeticRequestDto;
import com.example.calculator.dto.response.ArithmeticResponseDto;
import com.example.calculator.service.arithmeticOperations.Arithmetics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/operations")
@RestController
public class ArithmeticController {
  private final Arithmetics arithmetics;

  @PostMapping("/addition")
  public ResponseEntity<ArithmeticResponseDto> add(
      @RequestBody ArithmeticRequestDto arithmeticRequestDto) {
    return new ResponseEntity<>(arithmetics.add(arithmeticRequestDto), HttpStatus.OK);
  }

  @PostMapping("/subtraction")
  public ResponseEntity<ArithmeticResponseDto> subtract(
      @RequestBody ArithmeticRequestDto arithmeticRequestDto) {
    return new ResponseEntity<>(arithmetics.subtract(arithmeticRequestDto), HttpStatus.OK);
  }

  @PostMapping("/division")
  public ResponseEntity<ArithmeticResponseDto> divide(
      @RequestBody ArithmeticRequestDto arithmeticRequestDto) {
    return new ResponseEntity<>(arithmetics.divide(arithmeticRequestDto), HttpStatus.OK);
  }
}
