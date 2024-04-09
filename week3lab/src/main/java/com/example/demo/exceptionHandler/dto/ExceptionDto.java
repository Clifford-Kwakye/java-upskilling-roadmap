package com.example.demo.exceptionHandler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ExceptionDto {
  private String path;
  private String message;
  private final OffsetDateTime timeStamp = OffsetDateTime.now();
}
