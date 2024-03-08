package com.example.calculator.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
  private String path;
  private String message;
  private final OffsetDateTime timeStamp = OffsetDateTime.now();
}
