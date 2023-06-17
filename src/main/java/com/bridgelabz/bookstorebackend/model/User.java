package com.bridgelabz.bookstorebackend.model;

import com.bridgelabz.bookstorebackend.dto.UserDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String address;
    public String token;
    private boolean verify = false;

    public User(UserDTO userDTO) {
        this.userName = userDTO.username;
        this.password = userDTO.password;
        this.email = userDTO.email;
        this.address = userDTO.address;
    }

    public void updateData(UserDTO userDTO) {
        this.userName = userDTO.username;
        this.password = userDTO.password;
        this.email = userDTO.email;
        this.address = userDTO.address;
    }
}