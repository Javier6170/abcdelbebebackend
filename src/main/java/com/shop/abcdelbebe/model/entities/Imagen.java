package com.shop.abcdelbebe.model.entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="imagen")
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Column(unique = true, name = "name")
    private String name;
    @NotNull
    @Column(name = "type")
    private String type;
    @NotNull
    @Column(name = "picByte", length = 999999999)
    private byte[] picByte;


    public Imagen(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public Imagen() {

    }
}
