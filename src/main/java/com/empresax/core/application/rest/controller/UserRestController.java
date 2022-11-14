package com.empresax.core.application.rest.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.domain.api.UserApi;
import com.empresax.core.domain.model.User;
import com.empresax.core.domain.service.IUserService;

@RestController
@RequestMapping(value = "/api/users/v1")
public class UserRestController implements UserApi {

    @Autowired
    private IUserService userService;

    @Override
    public ResponseEntity<User> createUser(@Valid @RequestBody User User) {
        return new ResponseEntity<>(userService.create(User), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUserById(@Valid @RequestBody User User) {
        return new ResponseEntity<>(userService.updateById(User), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}