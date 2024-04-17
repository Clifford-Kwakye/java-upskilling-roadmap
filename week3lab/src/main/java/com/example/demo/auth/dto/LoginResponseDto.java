package com.example.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    private long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String role;
    private String token;
}
