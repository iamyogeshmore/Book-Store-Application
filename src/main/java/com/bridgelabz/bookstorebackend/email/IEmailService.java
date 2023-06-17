package com.bridgelabz.bookstorebackend.email;

import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.Email;

public interface IEmailService {
    ResponseEntity<ResponseDTO> sendMail(com.bridgelabz.bookstorebackend.email.Email email);

    String getLink(String token);
}
