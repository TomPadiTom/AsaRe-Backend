package com.padillatom.asadoremotobackend.model.response;

import com.padillatom.asadoremotobackend.model.entity.AccountDetailsEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetailsResponse {

    private String firstName;

    private String lastName;

    private String displayName;

    private String email;

    private String phone;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    public static AccountDetailsResponse toDto(AccountDetailsEntity entity) {
        if (entity == null) return null;

        return AccountDetailsResponse.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .displayName(entity.getDisplayName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
