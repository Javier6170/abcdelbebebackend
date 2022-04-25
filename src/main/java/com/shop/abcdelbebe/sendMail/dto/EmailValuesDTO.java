package com.shop.abcdelbebe.sendMail.dto;

import lombok.Data;

@Data
public class EmailValuesDTO {
        private String mailFrom;
        private String mailTo;
        private String subject;
        private String username;
        private String tokenPassword;

    public EmailValuesDTO() {
    }

    public EmailValuesDTO(String mailFrom, String mailTo, String subject, String username, String tokenPassword) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.username = username;
        this.tokenPassword = tokenPassword;
    }
}
