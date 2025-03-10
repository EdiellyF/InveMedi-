package com.br.InveMedi.inveMedi.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateDTO {


    private Long id;

    @NotBlank
    @Size(min = 8, max = 60)
    private String password;

    @NotBlank
    @Size(max = 60)
    private String email;
}
