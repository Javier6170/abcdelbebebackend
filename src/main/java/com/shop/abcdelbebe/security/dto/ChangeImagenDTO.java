package com.shop.abcdelbebe.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeImagenDTO {
    @NotBlank
    private String correo;
    @NotBlank
    private String nombre_imagen;

    public ChangeImagenDTO() {
    }

    public ChangeImagenDTO(String correo, String nombre_imagen) {
        this.correo = correo;
        this.nombre_imagen = nombre_imagen;
    }
}
