package com.alura.ForoHub.dto.user;

import com.alura.ForoHub.domain.model.ProfileType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDTO( @NotBlank String name,
                               @NotBlank @Email String email,
                               @NotBlank String password,
                               @NotNull ProfileType profileType
) {
}