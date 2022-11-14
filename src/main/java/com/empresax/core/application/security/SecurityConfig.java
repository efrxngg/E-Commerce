package com.empresax.core.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.empresax.core.application.security.jwt.JwtAuthorizationFilter;
import com.empresax.core.application.security.jwt.JwtUnAuthorizedEntryPoint;
import com.empresax.core.infrastructure.entity.RoleType;

// 1
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUnAuthorizedEntryPoint aUnAuthorizedEntryPoint;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain filteChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .antMatchers( "/api/v1/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/add").permitAll()
                .antMatchers("/api/admin/user/v1/**").hasAnyAuthority(RoleType.ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(aUnAuthorizedEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    protected AuthenticationManager AuthenticationManager(HttpSecurity httpSecurity)
            throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

}
