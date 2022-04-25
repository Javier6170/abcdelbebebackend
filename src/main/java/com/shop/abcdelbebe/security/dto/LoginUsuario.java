package com.shop.abcdelbebe.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginUsuario {
    @Email
    private String correo;
    @NotBlank
    private String password;
}
