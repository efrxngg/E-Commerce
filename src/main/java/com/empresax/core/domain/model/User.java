package com.empresax.core.domain.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "The username cannot be null")
    @NotBlank(message = "The username cannot be empty")
    private String username;

    @NotNull(message = "The password cannot be null")
    @NotBlank(message = "The password cannot be empty")
    private String password;

    @Email(message = "Invalid Email Format")
    @NotNull(message = "The email cannot be null")
    @NotBlank(message = "The email cannot be empty")
    private String email;

    @NotNull(message = "The fist_name cannot be null")
    @NotBlank(message = "The fist_name cannot be empty")
    private String fist_name;

    @NotNull(message = "The last_name cannot be null")
    @NotBlank(message = "The last_name cannot be empty")
    private String last_name;

    @Pattern(regexp = "^[0-9]{10,13}$", message = "The phone number is incomplete or contains non-numeric characters")
    @NotNull(message = "The phone_number cannot be null")
    @NotBlank(message = "The phone_number cannot be empty")
    private String phone_number;

}