package com.shop.abcdelbebe.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserRecover {
    @Email
    private String correo;

    public UserRecover(String correo) {
        this.correo = correo;
    }
}
