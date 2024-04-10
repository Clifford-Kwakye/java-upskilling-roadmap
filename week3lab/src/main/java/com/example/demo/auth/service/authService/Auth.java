package com.example.demo.auth.service.authService;

import com.example.demo.auth.dto.LoginRequestDto;
import com.example.demo.auth.dto.LoginResponseDto;
import com.example.demo.auth.dto.SignupRequestDto;
import com.example.demo.entities.User;

public interface Auth {
  User signup(SignupRequestDto signupRequestDto);

  LoginResponseDto login(LoginRequestDto loginRequestDto);
}
