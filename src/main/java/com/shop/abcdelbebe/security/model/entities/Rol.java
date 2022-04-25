package com.shop.abcdelbebe.security.model.entities;

import com.shop.abcdelbebe.security.enums.RolNombre;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="rol")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre nombre;

    public Rol(RolNombre nombre) {
        this.nombre = nombre;
    }

    public Rol() {

    }


}
