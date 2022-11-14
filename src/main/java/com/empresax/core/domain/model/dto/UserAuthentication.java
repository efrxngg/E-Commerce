package com.empresax.core.domain.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthentication implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "The username cannot be null and void")
    @NotBlank(message = "The username cannot be blank")
    private String username;

    @NotNull(message = "The password cannot be null and void")
    @NotBlank(message = "The password cannot be blank")
    private String password;

}