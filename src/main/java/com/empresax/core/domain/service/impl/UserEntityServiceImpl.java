package com.empresax.core.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresax.core.domain.exception.UserEntityNotFoundException;
import com.empresax.core.domain.service.IUserEntityService;
import com.empresax.core.infrastructure.entity.UserEntity;
import com.empresax.core.infrastructure.repository.IUserEntityCrudRepository;

@Service
public class UserEntityServiceImpl implements IUserEntityService {

    @Autowired
    private IUserEntityCrudRepository userEntityCrudRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity create(UserEntity entity) {
        entity.setId_user(null);
        entity.setDate_entry(new Date());
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userEntityCrudRepository.save(entity);
    }

    @Override
    public UserEntity findById(UUID id) {
        return userEntityCrudRepository.findById(id).orElseThrow(
                () -> new UserEntityNotFoundException(String.format("UserEntity with id '%s' not found", id)));
    }

    @Override
    public List<UserEntity> findAll() {
        return userEntityCrudRepository.findAll();
    }

    @Transactional
    @Override
    public UserEntity updateById(UserEntity entity) {
        if (userEntityCrudRepository.existsById(entity.getId_user()))
            throw new UserEntityNotFoundException(
                    String.format("UserEntity with id '%s' not found", entity.getId_user()));

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userEntityCrudRepository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        userEntityCrudRepository.sp_deletionlogical(id);
    }

}