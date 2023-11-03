package com.padillatom.asadoremotobackend.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    @Email
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 30)
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    private Integer roleId;
}
