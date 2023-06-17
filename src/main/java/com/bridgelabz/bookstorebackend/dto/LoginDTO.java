package com.bridgelabz.bookstorebackend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginDTO {
    public String email;
    public String password;
}
