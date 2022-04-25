package com.shop.abcdelbebe.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class NuevoUsuario {
    @NotBlank
    private String nombre;
    @NotBlank
    private String telefono;
    @Email
    private String correo;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();

    public NuevoUsuario(String nombre, String telefono, String correo, String password, Set<String> roles) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
        this.roles = roles;
    }
}
