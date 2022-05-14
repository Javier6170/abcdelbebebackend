package com.shop.abcdelbebe.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class ProductoDto {
    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotBlank
    private int precio;

    @NotBlank
    private int cantidad;

    public ProductoDto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public ProductoDto() {
    }

    public ProductoDto(String nombre, String descripcion, int precio, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}
