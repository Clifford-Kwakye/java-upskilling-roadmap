package com.example.demo.config;

import com.example.demo.auth.dto.LoginResponseDto;
import com.example.demo.auth.service.jwt.JwtAuthFilter;
import com.example.demo.auth.service.jwt.JwtService;
import com.example.demo.auth.service.userDetails.UserDetailsServiceImpl;
import com.example.demo.entities.User;
import com.example.demo.exceptionHandler.dto.ExceptionDto;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
  private final UserDetailsServiceImpl userDetailsService;
  private final JwtAuthFilter jwtAuthFilter;
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private static final String[] PUBLIC_URLS = {"/api/v1/auth/**"};
  private static final String CONTENT_TYPE = "application/json";

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            request ->
                request.requestMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin(
            form ->
                form.loginProcessingUrl("/api/v1/auth/login")
                    .usernameParameter("email")
                    .successHandler(this::authenticationSuccessHandler)
                    .failureHandler(this::authenticationFailureHandler)
                    .permitAll())
        .build();
  }

  private void authenticationFailureHandler(
      HttpServletRequest request,
      HttpServletResponse httpServletResponse,
      AuthenticationException e)
      throws IOException {
    httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    httpServletResponse.setContentType(CONTENT_TYPE);
    new ObjectMapper()
        .writeValue(
            httpServletResponse.getOutputStream(),
            new ExceptionDto(request.getRequestURI(), e.getMessage()));
  }

  private void authenticationSuccessHandler(
      HttpServletRequest request,
      HttpServletResponse httpServletResponse,
      Authentication authentication)
      throws IOException {
    httpServletResponse.setContentType(CONTENT_TYPE);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    User user =
        userRepository
            .findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException(""));

    new ObjectMapper()
        .writeValue(
            httpServletResponse.getOutputStream(),
            LoginResponseDto.builder()
                .token(jwtService.generateToken(user.getEmail()))
                .user(user)
                .build());

    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }
}
