package com.empresax.core.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresax.core.infrastructure.entity.TokenEntity;

public interface ITokenRepository extends JpaRepository<TokenEntity, UUID> {

    Optional<TokenEntity> findByValue(String token);

    boolean existsByValue(String token);

}
