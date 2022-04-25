package com.shop.abcdelbebe.sendMail.dto;

import lombok.Data;

@Data
public class WelcomeUserDTO {
    private String mailTo;
    private String mailFrom;

    public WelcomeUserDTO() {
    }

    public WelcomeUserDTO(String mailTo) {
        this.mailTo = mailTo;
    }
}
