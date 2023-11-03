package com.padillatom.asadoremotobackend.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {

    private String firstName;

    private String lastName;

    private String companyName;

    private String country;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private String phone;

    private String email;
}
