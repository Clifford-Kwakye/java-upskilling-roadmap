package com.example.calculator.arithmeticOperations.controller;

import com.example.calculator.arithmeticOperations.dto.request.ArithmeticRequestDto;
import com.example.calculator.arithmeticOperations.dto.response.ArithmeticResponseDto;
import com.example.calculator.arithmeticOperations.service.Arithmetics;
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
public class AdditionController {
    private final Arithmetics addition;

    @PostMapping("/addition")
    public ResponseEntity<ArithmeticResponseDto> add(@RequestBody ArithmeticRequestDto additionRequestDto) {
        return new ResponseEntity<>(addition.add(additionRequestDto), HttpStatus.OK);
    }
}
