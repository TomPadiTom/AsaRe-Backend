package com.padillatom.asadoremotobackend.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class SimpleAddressEntity extends AuditableEntity {

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
