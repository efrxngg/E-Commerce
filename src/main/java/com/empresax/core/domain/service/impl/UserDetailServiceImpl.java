package com.empresax.core.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empresax.core.infrastructure.repository.IUserEntityCrudRepository;

// 2
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserEntityCrudRepository userEntityCrudRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
         * if it throws the exception UsernameNotFoundException if performs a search again
         */
        return new UserDetailsImpl(userEntityCrudRepository.findByUsernameOrEmail(username).get());
    }

}