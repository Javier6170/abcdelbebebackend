package com.shop.abcdelbebe.model.entities;

import com.shop.abcdelbebe.security.model.entities.Rol;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    @NotNull
    private int precio;

    @NotNull
    private int cantidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria id_Categoria;

    @NotNull
    private String nombre_imagen;

}
