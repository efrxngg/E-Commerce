package com.empresax.core.application.rest.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.domain.api.UserEntityApi;
import com.empresax.core.domain.service.IUserEntityService;
import com.empresax.core.infrastructure.entity.UserEntity;

// import io.swagger.v3.oas.annotations.Hidden;

// @Hidden
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserEntityRestController implements UserEntityApi {

    @Autowired
    private IUserEntityService userEntityService;

    @Override
    public ResponseEntity<UserEntity> saveUserEntity(@Valid @RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(userEntityService.create(userEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserEntity>> findAllUsersEntity() {
        var result = userEntityService.findAll();
        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> findUserEntityById(@PathVariable UUID id) {
        return new ResponseEntity<>(userEntityService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> updateUserEntityById(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userEntityService.updateById(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserEntityById(UUID id) {
        userEntityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}