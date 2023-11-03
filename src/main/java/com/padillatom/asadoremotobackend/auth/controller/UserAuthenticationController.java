package com.padillatom.asadoremotobackend.auth.controller;

import com.padillatom.asadoremotobackend.model.response.AuthenticationResponse;
import com.padillatom.asadoremotobackend.model.response.RegisterResponse;
import com.padillatom.asadoremotobackend.service.CustomAuthenticationService;
import com.padillatom.asadoremotobackend.model.request.AuthenticationRequest;
import com.padillatom.asadoremotobackend.model.request.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller")
public class UserAuthenticationController {

    private final CustomAuthenticationService customAuthenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Registro",
            description = "Registro de un nuevo usuario.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Registro exitoso.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RegisterResponse.class)
                            )
                    )
            }
    )
    public RegisterResponse signUp(@Valid @RequestBody RegisterRequest userToCreate) {
        return customAuthenticationService.registerUser(userToCreate);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(
            summary = "Login",
            description = "Login de usuario por Email y Contraseña.",
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Login exitoso + JWT.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class)
                            )
                    )
            }
    )
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest userToLogin) {
        return customAuthenticationService.loginUser(userToLogin);
    }
}
