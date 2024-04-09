package com.example.demo.exceptionHandler;

import com.example.demo.exceptionHandler.dto.ExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(
      value = {UsernameNotFoundException.class})
  public ResponseEntity<ExceptionDto> handleBadRequest(
      UsernameNotFoundException usernameNotFoundException, HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(
        new ExceptionDto(
            httpServletRequest.getRequestURI(), usernameNotFoundException.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(
      value = {IllegalArgumentException.class})
  public ResponseEntity<ExceptionDto> handleIllegalArgumentException(
      IllegalArgumentException illegalArgumentException, HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(
        new ExceptionDto(httpServletRequest.getRequestURI(), illegalArgumentException.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {HttpMediaTypeNotAcceptableException.class})
  public ResponseEntity<ExceptionDto> handleHttpMediaTypeNotAcceptableException(
      HttpMediaTypeNotAcceptableException httpMediaTypeNotAcceptableException,
      HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(
        new ExceptionDto(
            httpServletRequest.getRequestURI(), httpMediaTypeNotAcceptableException.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {InternalAuthenticationServiceException.class})
  public ResponseEntity<ExceptionDto> handleInternalAuthenticationServiceException(
          InternalAuthenticationServiceException internalAuthenticationServiceException,
          HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(
            new ExceptionDto(
                    httpServletRequest.getRequestURI(), internalAuthenticationServiceException.getMessage()),
            HttpStatus.BAD_REQUEST);
  }
}
