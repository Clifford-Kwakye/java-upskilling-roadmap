package com.example.demo.auth.service.userDetails;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
    System.out.println(user);
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        user.isEnabled(),
        true,
        true,
        true,
        getAuthorities(user));
  }

  private Collection<GrantedAuthority> getAuthorities(User user) {
    return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
  }
}
