package com.bridgelabz.bookstorebackend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@RequiredArgsConstructor
public class UserDTO {
    @NotNull(message = "User Name cannot be null")
    // UserName contain at least 3 characters
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "User Name Is Invalid")
    public String username;

    @NotNull(message = "Password cannot be null")
    //Password contains minimum 1 number , special character and lenght should be more than 8
    @Pattern(regexp = "^^(?=.*[0-9])(?=.*[!@#$%&^*)_(+}{?>:<]){1}+(?=.*[A-Z])([0-9A-Za-z]).{8,}$", message = "Password Is Invalid")
    public String password;

    @NotNull(message = "Email cannot be null")
    //Email contains alphabets and numeric with special character "@"
    @Pattern(regexp = "^[A-Za-z0-9]+(.[A-Za-z0-9]+)*@[^_\\W]+(.[^_\\W]+)?(?=(.[^_\\W]{3,}$|.[a-zA-Z]{2}$)).*$", message = "Email Is Invalid")
    public String email;

    @NotNull(message = "Address cannot be null")
    //Address contains at least 1 character and number
    @Pattern(regexp = "^((.){1,}(\\d){1,}(.){0,})$", message = "Address Is Invalid")
    public String address;

//    public String token;
}