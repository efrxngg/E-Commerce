package com.empresax.core.infrastructure.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.empresax.core.domain.model.User;
import com.empresax.core.domain.repository.IUserRepository;
import com.empresax.core.infrastructure.config.Mapper;
import com.empresax.core.infrastructure.entity.RoleType;
import com.empresax.core.infrastructure.entity.StateType;
import com.empresax.core.infrastructure.entity.UserEntity;
import com.empresax.core.infrastructure.repository.IUserEntityCrudRepository;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private IUserEntityCrudRepository userEnitityCrudRepository;

    @Override
    public User save(User entity) {
        UserEntity userEntity = Mapper.toUserEntity(entity);
        userEntity.setDate_entry(new Date(System.currentTimeMillis()));
        userEntity.setRole(RoleType.USER);
        userEntity.setState(StateType.ACTIVE);
        entity.setId_user(userEnitityCrudRepository.save(userEntity).getId_user());
        return entity;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.of(Mapper.toUser(userEnitityCrudRepository.findById(id).get()));
    }

    @Override
    public List<User> findAll() {
        return userEnitityCrudRepository.findAll().stream()
                .map(Mapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public User update(User entity) {
        userEnitityCrudRepository.sp_UpdateUser(
                entity.getId_user(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getFist_name(),
                entity.getLast_name(),
                entity.getEmail(),
                entity.getPhone_number());
        return entity;
    }

    @Override
    public void delete(UUID id) {
        if (userEnitityCrudRepository.existsById(id))
            userEnitityCrudRepository.sp_deletionlogical(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.of(userEnitityCrudRepository.findByUsername(username).get());
    }

}
