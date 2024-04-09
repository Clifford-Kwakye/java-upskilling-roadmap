package com.example.demo.auth.dto;

import com.example.demo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private User user;
}
