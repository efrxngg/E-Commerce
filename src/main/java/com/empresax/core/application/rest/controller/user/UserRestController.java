package com.empresax.core.application.rest.controller.user;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresax.core.application.security.jwt.JwtUtil;
import com.empresax.core.domain.api.UserApi;
import com.empresax.core.domain.model.User;
import com.empresax.core.domain.service.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/user")
@AllArgsConstructor
public class UserRestController implements UserApi {

    private IUserService userService;
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<User> createUser(@Valid @RequestBody User User) {
        return new ResponseEntity<>(userService.create(User), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUserById(@Valid @RequestBody User User, HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        return new ResponseEntity<>(userService.updateById(
                User, UUID.fromString(claim.get("id", String.class))),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(HttpServletRequest request) {
        var claim = jwtUtil.getClaims(request.getHeader("Authorization").replace("Bearer ", ""));
        userService.deleteById(UUID.fromString(claim.get("id", String.class)));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}