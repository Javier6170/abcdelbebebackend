package com.shop.abcdelbebe.sendMail.dto;

import lombok.Data;

@Data
public class VentaUserDTO {
    private String mailTo;
    private String mailFrom;
    private String estadoVenta;
    private String nombreProducto;
    private String direccion;

    public VentaUserDTO() {
    }

    public VentaUserDTO(String mailTo) {
        this.mailTo = mailTo;
    }
}
