package com.example.demo.auth.service.authService;

import com.example.demo.auth.dto.LoginRequestDto;
import com.example.demo.auth.dto.LoginResponseDto;
import com.example.demo.auth.dto.SignupRequestDto;

public interface Auth {
  SignupRequestDto signup(SignupRequestDto signupRequestDto);

  LoginResponseDto login(LoginRequestDto loginRequestDto);
}
