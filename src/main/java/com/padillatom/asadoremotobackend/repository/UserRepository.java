package com.padillatom.asadoremotobackend.repository;

import com.padillatom.asadoremotobackend.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByAccountDetailsEmail(String email);
}
