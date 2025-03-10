package com.br.InveMedi.inveMedi.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserCreateDTO {
    @Column(name = "username", length = 100, nullable = false, unique = true)
    @Size( min = 2, max = 100)
    @NotBlank
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Size(min = 2, max = 100)
    @NotBlank
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @Size( min = 6, max = 60)
    @NotBlank
    private String password;
}
