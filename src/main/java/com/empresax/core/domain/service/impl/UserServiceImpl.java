package com.empresax.core.domain.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresax.core.domain.exception.UserNotAvailable;
import com.empresax.core.domain.exception.UserNotFoundException;
import com.empresax.core.domain.model.User;
import com.empresax.core.domain.repository.IUserRepository;
import com.empresax.core.domain.service.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(User entity) {
        if(!userRepository.findByUsernameOrEmail(entity.getUsername(), entity.getEmail()).isEmpty())
            throw new UserNotAvailable("Username or Email not available");
        var aux = entity.getPassword();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
        entity.setPassword(aux);

        return entity;
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("The User with ID '%s' not found", id)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateById(User entity, UUID id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException(String.format("The User with ID '%s' not found", id));

        var aux = entity.getPassword();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.update(entity, id);
        entity.setPassword(aux);
        return entity;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        userRepository.delete(id);
    }

}