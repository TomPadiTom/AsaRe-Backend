package com.padillatom.asadoremotobackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class RoleEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdDate;

    private String createdBy;

    private OffsetDateTime modifiedDate;

    private String modifiedBy;

    private boolean deleted;

    @PrePersist
    private void onCreate() {
        createdDate = OffsetDateTime.now();
    }

}
