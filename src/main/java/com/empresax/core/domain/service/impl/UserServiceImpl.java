package com.empresax.core.domain.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresax.core.domain.exception.UserEntityNotFoundException;
import com.empresax.core.domain.model.User;
import com.empresax.core.domain.repository.IUserRepository;
import com.empresax.core.domain.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserEntityNotFoundException(String.format("The User with ID '%s' not found", id)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateById(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.update(entity);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        userRepository.delete(id);
    }

}