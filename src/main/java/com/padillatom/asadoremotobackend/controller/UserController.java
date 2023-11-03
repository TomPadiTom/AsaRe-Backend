package com.padillatom.asadoremotobackend.controller;

import com.padillatom.asadoremotobackend.service.CustomUserDetailsService;
import com.padillatom.asadoremotobackend.service.UserService;
import com.padillatom.asadoremotobackend.model.request.UserDetailsUpdateRequest;
import com.padillatom.asadoremotobackend.model.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "ADMIN - Detalles del Usuario por ID",
            description = "Obtiene todos los datos de un Usuario por ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalles de Usuario"
                    )
            }
    )
    public UserResponse getUserDetailsById(@PathVariable Long id) {
        return userService.findByUserId(id);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Detalles del Usuario por JWT",
            description = "Obtiene todos los datos de un Usuario en SecurityContext por JWT.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalles de Usuario"
                    )
            }
    )
    public UserResponse meData(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return userService.meData(authentication.getName());
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Update Detalles del Usuario por JWT",
            description = "Update todos los datos de un Usuario en SecurityContext por JWT.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update Detalles de Usuario"
                    )
            }
    )
    public UserResponse updateMeData(@CurrentSecurityContext(expression = "authentication") Authentication authentication,
                                     @Valid @RequestBody UserDetailsUpdateRequest request) {
        return userService.userUpdate(authentication.getName(), request);
    }
}