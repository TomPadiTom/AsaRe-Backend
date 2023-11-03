package com.padillatom.asadoremotobackend.service;

import com.padillatom.asadoremotobackend.auth.utils.JwtUtil;
import com.padillatom.asadoremotobackend.model.entity.UserEntity;
import com.padillatom.asadoremotobackend.model.mapper.AuthenticationMapper;
import com.padillatom.asadoremotobackend.model.request.AuthenticationRequest;
import com.padillatom.asadoremotobackend.model.request.RegisterRequest;
import com.padillatom.asadoremotobackend.model.response.AuthenticationResponse;
import com.padillatom.asadoremotobackend.model.response.RegisterResponse;
import com.padillatom.asadoremotobackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private static final String REGISTER_EMAIL_IN_USE_MESSAGE = "Esta dirección de correo electrónico ya está registrada.";

    @Transactional
    public RegisterResponse registerUser(RegisterRequest req) {

        var matchingUser = userRepository.findByAccountDetailsEmail(req.getEmail());
        
        if (matchingUser.isPresent()) {
            throw new IllegalArgumentException(REGISTER_EMAIL_IN_USE_MESSAGE);
        } else {
            req.setPassword(passwordEncoder.encode(req.getPassword()));
            UserEntity savedUser = userRepository.save(AuthenticationMapper.toEntity(req));
            userRepository.flush();
            String jwt = jwtUtil.generate(savedUser.getAccountDetails().getEmail());
            return AuthenticationMapper.registerToDto(savedUser, jwt);
        }
    }

    public AuthenticationResponse loginUser(AuthenticationRequest userToLogin) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userToLogin.getEmail(), userToLogin.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        String jwt = jwtUtil.generate(userToLogin.getEmail());

        return AuthenticationMapper.authenticationToDto(authentication, jwt);
    }
}
