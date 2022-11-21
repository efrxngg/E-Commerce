package com.empresax.core.infrastructure.dao;

import java.sql.Timestamp;
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
        UserEntity userEntity = Mapper.to(entity, null);
        userEntity.setDate_entry(new Timestamp(System.currentTimeMillis()));
        userEntity.setRole(RoleType.USER);
        userEntity.setState(StateType.ACTIVE);
        userEnitityCrudRepository.save(userEntity);
        return entity;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.of(Mapper.to(userEnitityCrudRepository.findById(id).get()));
    }

    @Override
    public List<User> findAll() {
        return userEnitityCrudRepository.findAll().stream()
                .map(Mapper::to)
                .collect(Collectors.toList());
    }

    @Override
    public User update(User entity, UUID id) {
        userEnitityCrudRepository.updateUser(
                id,
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
            userEnitityCrudRepository.deletionLogical(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userEnitityCrudRepository.findByUsername(username);
    }

    @Override
    public boolean existsById(UUID id) {
        return userEnitityCrudRepository.existsById(id);
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String option) {
        var user = userEnitityCrudRepository.findByUsernameOrEmail(option);
        if (user.isEmpty())
            return Optional.empty();

        return Optional.of(Mapper.to(user.get()));
    }

    @Override
    public List<User> findByUsernameOrEmail(String username, String email) {
        var result = userEnitityCrudRepository.findByUsernameOrEmail(username, email);

        return result.stream().map(Mapper::to).collect(Collectors.toList());
    }

}
