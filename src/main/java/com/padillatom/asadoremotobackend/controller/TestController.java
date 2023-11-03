package com.padillatom.asadoremotobackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
@Tag(name = "Test Controller")
public class TestController {

    @GetMapping("/admin")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "ADMIN - Test JWT para ROLE_ADMIN",
            description = "Test de la ruta protegida por JWT. Se espera una JWT con ROLE_ADMIN",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "JWT Corresponde a ROLE_ADMIN",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = @ExampleObject("Bienvenido a eCommerce Backend - ADMIN")
                            )
                    )
            }
    )
    public String testAdmin() {
        return "Bienvenido a eCommerce Backend - ADMIN";
    }

    @GetMapping("/user")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "USER - Test JWT para ROLE_USER",
            description = "Test de la ruta protegida por JWT. Se espera una JWT con ROLE_USER",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "JWT Corresponde a ROLE_USER",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = @ExampleObject("Bienvenido a eCommerce Backend - USER")
                            )
                    )
            }
    )
    public String testUser() {
        return "Bienvenido a eCommerce Backend - USER";
    }

    @GetMapping("/any")
    @Operation(
            summary = "Test ruta sin JWT",
            description = "Test de la ruta no protegida por JWT.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ruta accesible por 'any'",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = @ExampleObject("Bienvenido a eCommerce Backend - ANY")
                            )
                    )
            }
    )
    public String test() {
        return "Bienvenido a eCommerce Backend - ANY";
    }

}
