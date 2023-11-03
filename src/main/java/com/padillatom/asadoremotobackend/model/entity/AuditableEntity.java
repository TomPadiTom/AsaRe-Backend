package com.padillatom.asadoremotobackend.model.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.OffsetDateTime;

import static java.util.Optional.ofNullable;

@Getter
@Setter
@MappedSuperclass
public class AuditableEntity {

    private OffsetDateTime createdDate = OffsetDateTime.now();

    private OffsetDateTime modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private boolean deleted;

    @PrePersist
    public void onPrePersist() {
        this.createdBy = getUsername();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = OffsetDateTime.now();
        this.modifiedBy = getUsername();
    }

    private String getUsername() {
        return ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getName).orElse("Anonymous");
    }
}
