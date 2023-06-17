package com.bridgelabz.bookstorebackend.service;


import com.bridgelabz.bookstorebackend.dto.LoginDTO;
import com.bridgelabz.bookstorebackend.dto.UserDTO;
import com.bridgelabz.bookstorebackend.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getUserData();

    User addUserData(UserDTO userDTO);

    User getUserDataById(String token);

    User updateUserData(String token, UserDTO userDTO);

    String deleteUserData(String token);

    Optional<User> userLogin(LoginDTO loginDTO);

    Object forgotPassword(LoginDTO loginDTO);

    User verifyUser(String token);
}