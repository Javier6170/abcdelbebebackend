package com.shop.abcdelbebe.sendMail.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordDTO {
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String tokenPassword;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(String password, String confirmPassword, String tokenPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.tokenPassword = tokenPassword;
    }
}
