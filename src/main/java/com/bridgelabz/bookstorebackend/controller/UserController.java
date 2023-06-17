package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.LoginDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.dto.UserDTO;
import com.bridgelabz.bookstorebackend.exception.BookStoreException;
import com.bridgelabz.bookstorebackend.service.IUserService;
import com.bridgelabz.bookstorebackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;
    List<User> userList = new ArrayList<>();

    //--------------------------------- Get All User Data ---------------------------------

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getUserData() {
        userList = iUserService.getUserData();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successful", userList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Add New User Data ---------------------------------

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addUserData(
            @Valid @RequestBody UserDTO userDTO) {
        User userData = iUserService.addUserData(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added User Data Successfully", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Get User By Id ---------------------------------

    @GetMapping("/get/{token}")
    public ResponseEntity<ResponseDTO> getUserDataById(@PathVariable String token) {
        Object user = iUserService.getUserDataById(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Call From Token Id Successful", user);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Update User Data ---------------------------------

    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateUserData(@PathVariable String token, @Valid @RequestBody UserDTO userDTO) {
        User userData = iUserService.updateUserData(token, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated User Details Successfully", userData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Delete User Data ---------------------------------

    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteUserData(@PathVariable String token) {
        iUserService.deleteUserData(token);
        ResponseDTO responseDTO = new ResponseDTO("Data Deleted Successfully", "Deleted token id:" + token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- User Login ---------------------------------

    @PostMapping("/userLogin")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody LoginDTO loginDTO) {
        Optional<User> login = iUserService.userLogin(loginDTO);
        if (login != null) {
            ResponseDTO responseDTO = new ResponseDTO("Login Successful", login);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        } else {
            ResponseDTO responseDTO = new ResponseDTO("User login not successfully", login);
            throw (new BookStoreException("Record not Found"));
        }
    }
    //--------------------------------- Forgot Password ---------------------------------

    @PostMapping("/forgotPassword")
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestBody LoginDTO loginDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Checking password for given email id", iUserService.forgotPassword(loginDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Verify User ---------------------------------
    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyUser(@PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("User verified successfully", iUserService.verifyUser(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
