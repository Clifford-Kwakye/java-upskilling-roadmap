package com.example.demo.auth.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
