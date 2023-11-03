package com.padillatom.asadoremotobackend.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account_details")
@SQLDelete(sql = "UPDATE account_details SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class AccountDetailsEntity extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String displayName;

    private String email;

    private String password;

    private String phone;

    @JsonFormat(pattern = "yyyy/mm/dd")
    private LocalDate birthDate;
}
