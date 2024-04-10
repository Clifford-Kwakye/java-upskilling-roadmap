package com.example.demo.auth.controller;

import com.example.demo.auth.dto.LoginRequestDto;
import com.example.demo.auth.dto.LoginResponseDto;
import com.example.demo.auth.dto.SignupRequestDto;
import com.example.demo.auth.service.authService.AuthService;
import com.example.demo.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<User> signup(@RequestBody SignupRequestDto signupRequestDto) {
    return new ResponseEntity<>(authService.signup(signupRequestDto), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
    return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.OK);
  }
}
