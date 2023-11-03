package com.padillatom.asadoremotobackend.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    @Email
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 30)
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 20)
    private String password;
}
