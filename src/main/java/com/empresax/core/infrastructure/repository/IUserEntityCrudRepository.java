package com.empresax.core.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresax.core.infrastructure.entity.UserEntity;

public interface IUserEntityCrudRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);
    
    @Modifying
    @Query(value = "call sp_updateuser(:id, :username, :password, :first_name, :last_name, :email, :phone_number)", nativeQuery = true)
    void sp_UpdateUser(
            @Param("id") UUID id,
            @Param("username") String username,
            @Param("password") String password,
            @Param("first_name") String first_name,
            @Param("last_name") String last_name,
            @Param("email") String email,
            @Param("phone_number") String phone_number);

    @Modifying
    @Query(value = "call sp_deletionlogical(:id)", nativeQuery = true)
    void sp_deletionlogical(@Param("id") UUID id);
}
