package com.padillatom.asadoremotobackend.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private String email;
    
    private String jwt;
}
