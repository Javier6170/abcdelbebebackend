package com.shop.abcdelbebe.model.entities;

import com.shop.abcdelbebe.security.model.entities.Usuario;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="venta")
public class Venta {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String estado;

    @NotNull
    @Column(name="fecha_compra")
    private Date fechaCompra;

    @ManyToOne
    @JoinColumn(name= "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
