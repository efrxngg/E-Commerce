package com.empresax.core.application.rest.controller.admin;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.domain.api.UserEntityApi;
import com.empresax.core.infrastructure.entity.UserEntity;
import com.empresax.core.infrastructure.repository.IUserEntityCrudRepository;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;

@Hidden
@RestController
@RequestMapping(value = "/api/v1/admin/users")
@AllArgsConstructor
public class UserEntityRestController implements UserEntityApi {

    private IUserEntityCrudRepository userEntityService;

    @Override
    public ResponseEntity<UserEntity> saveUserEntity(@Valid @RequestBody UserEntity userEntity) {
        userEntity.setId_user(null);
        return new ResponseEntity<>(userEntityService.save(userEntity), HttpStatus.OK);
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
        return new ResponseEntity<>(userEntityService.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> updateUserEntityById(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userEntityService.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserEntityById(@PathVariable UUID id) {
        userEntityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}