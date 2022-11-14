package com.empresax.core.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.empresax.core.domain.model.User;
import com.empresax.core.infrastructure.entity.UserEntity;

public interface IUserRepository extends IBaseRepository<User, UUID> {
    
    Optional<UserEntity> findByUsername(String username);
}
