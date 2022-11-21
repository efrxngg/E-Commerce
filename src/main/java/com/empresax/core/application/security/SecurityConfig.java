package com.empresax.core.application.security;

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

import lombok.AllArgsConstructor;

// 1
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JwtUnAuthorizedEntryPoint aUnAuthorizedEntryPoint;

    private JwtAuthorizationFilter jwtAuthorizationFilter;

    private UserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain filteChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/user/add").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                .antMatchers("/api/v1/admin/users/**",
                        "/api/v1/admin/products/**",
                        "/api/v1/admin/tags/**",
                        "/api/v1/admin/carts/**",
                        "/api/v1/admin/invoice-header/**",
                        "/api/v1/admin/invoice-detail/**"
                        ).hasAnyAuthority(RoleType.ADMIN.getAuthority())
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