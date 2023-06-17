package com.bridgelabz.bookstorebackend.dto;

import lombok.Data;

import javax.mail.internet.MimeMessage;

@Data
public class ResponseDTO {
    private String message;
    private Object data;

    public ResponseDTO(String message, MimeMessage mail, Object o) {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}