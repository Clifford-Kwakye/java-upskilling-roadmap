package com.example.calculator.exceptionHandler;

import com.example.calculator.dto.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({ArithmeticException.class})
  ResponseEntity<ErrorResponseDto> handleArithmeticException(
      ArithmeticException e, HttpServletRequest request) {
    return new ResponseEntity<>(
        new ErrorResponseDto(request.getRequestURI(), e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({IllegalArgumentException.class})
  ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(
      IllegalArgumentException e, HttpServletRequest request) {
    return new ResponseEntity<>(
        new ErrorResponseDto(request.getRequestURI(), e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({HttpServerErrorException.class})
  ResponseEntity<ErrorResponseDto> handleInternalServerError(
          HttpServerErrorException e, HttpServletRequest request) {
    return new ResponseEntity<>(
            new ErrorResponseDto(request.getRequestURI(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
