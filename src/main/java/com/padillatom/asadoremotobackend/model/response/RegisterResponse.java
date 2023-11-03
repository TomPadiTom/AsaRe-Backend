package com.padillatom.asadoremotobackend.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {

    private String email;

    private String firstName;

    private String lastName;

    private String jwt;
}
