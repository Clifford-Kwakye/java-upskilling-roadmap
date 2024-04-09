package com.example.demo.auth.service.authService;

import static com.example.demo.enums.Role.*;

import com.example.demo.auth.dto.LoginRequestDto;
import com.example.demo.auth.dto.LoginResponseDto;
import com.example.demo.auth.dto.SignupRequestDto;
import com.example.demo.auth.dto.SignupResponseDto;
import com.example.demo.auth.service.jwt.JwtService;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements Auth {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public SignupRequestDto signup(SignupRequestDto signupRequestDto) {
    Optional<User> optionalUser = userRepository.findByEmail(signupRequestDto.getEmail());

    if (optionalUser.isPresent()) throw new IllegalArgumentException("User already exists");

    User user =
        User.builder()
            .firstname(signupRequestDto.getFirstname())
            .lastname(signupRequestDto.getLastname())
            .username(signupRequestDto.getUsername())
            .email(signupRequestDto.getEmail())
            .password(passwordEncoder.encode(signupRequestDto.getPassword()))
            .role(USER.name())
            .build();
    userRepository.save(user);

    SignupResponseDto signupResponseDto = new SignupResponseDto();
    signupResponseDto.setFirstname(signupRequestDto.getFirstname());
    signupResponseDto.setLastname(signupRequestDto.getLastname());
    signupResponseDto.setUsername(signupRequestDto.getUsername());
    signupResponseDto.setEmail(signupRequestDto.getEmail());

    return signupResponseDto;
  }

  @Override
  public LoginResponseDto login(LoginRequestDto loginRequestDto) {
    String email = loginRequestDto.getEmail();

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, loginRequestDto.getPassword()));

    if (!authentication.isAuthenticated())
      throw new UsernameNotFoundException("Incorrect username or password");

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

    return LoginResponseDto.builder().token(jwtService.generateToken(email)).user(user).build();
  }
}
