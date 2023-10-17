package com.lucas.lucas.auth;

import com.lucas.lucas.model.User;
import com.lucas.lucas.model.UserRole;
import com.lucas.lucas.repository.UserRepository;
import com.lucas.lucas.security.JwtSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtSerivce jwtSerivce;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .last_name(request.getLast_name())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();
        repository.save(user);

        var jwtToken = jwtSerivce.generateTokenS((UserDetails) user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.getByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtSerivce.generateTokenS((UserDetails) user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
