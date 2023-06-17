package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.LoginDTO;
import com.bridgelabz.bookstorebackend.dto.UserDTO;
import com.bridgelabz.bookstorebackend.email.Email;
import com.bridgelabz.bookstorebackend.email.IEmailService;
import com.bridgelabz.bookstorebackend.exception.BookStoreException;
import com.bridgelabz.bookstorebackend.model.User;
import com.bridgelabz.bookstorebackend.repository.UserRepository;
import com.bridgelabz.bookstorebackend.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    IEmailService iEmailService;

    //--------------------------------- Get All User Data ---------------------------------
    @Override
    public List<User> getUserData() {
        return userRepository.findAll();
    }

    //--------------------------------- Add New User Data ---------------------------------
    @Override
    public User addUserData(UserDTO userDTO) {
        User userData = new User(userDTO);
        userRepository.save(userData);
        String token = tokenUtil.createToken(userData.getId());
        userData.setToken(token);

        Email email = new Email(userData.getEmail(), "User Is Registered",
                "Token: " + userData.getToken() + "\n" + " Click on below link for verification =>" + "\n" + iEmailService.getLink(token));
        iEmailService.sendMail(email);
        userRepository.save(userData);
        return userData;
    }

    //--------------------------------- Get User By Token Id ---------------------------------
    @Override
    public User getUserDataById(String token) {
        int id = tokenUtil.decodeToken(token);
        return userRepository.findById(id)
                .orElseThrow(() -> new BookStoreException("User  with id " + id + " does not exist in database..!"));
    }

    //--------------------------------- Update User Data ---------------------------------
    @Override
    public User updateUserData(String token, UserDTO userDTO) {
        int id = tokenUtil.decodeToken(token);
        User updatedData = this.getUserDataById(id);
        updatedData.updateData(userDTO);
        return userRepository.save(updatedData);
    }

    private User getUserDataById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BookStoreException("User  with id " + id + " does not exist in database..!"));
    }

    //--------------------------------- Delete User Data ---------------------------------
    @Override
    public String deleteUserData(String token) {
        int id = tokenUtil.decodeToken(token);
        Optional<User> userDetails = userRepository.findById(id);
        if (userDetails.isPresent()) {
            userRepository.deleteById(Math.toIntExact(id));
            return "Token Id is deleted.";
        }
        throw new BookStoreException("Token id is not found");
    }

    //--------------------------------- User Login ---------------------------------
    @Override
    public Optional<User> userLogin(LoginDTO loginDTO) {
        Optional<User> newUserRegistration =
                Optional.ofNullable(userRepository.findByEmailIdAndPassword(loginDTO.email, loginDTO.password));
        if (newUserRegistration.isPresent()) {
            log.info("Login Successful");
            return newUserRegistration;
        } else {
            System.out.println("User Not Found Exception:");
            throw (new BookStoreException("Record not Found."));
        }
    }

    //--------------------------------- Forgot Password ---------------------------------
    @Override
    public Object forgotPassword(LoginDTO loginDTO) {
        User user = userRepository.forgotPassword(loginDTO.email);
        if (user != null) {
            return "Password for " + loginDTO.email + " is " + user.getPassword();
        } else {
            return "Please Enter Valid Email Id And Try Again!";
        }
    }

    //--------------------------------- Verify User ---------------------------------
    @Override
    public User verifyUser(String token) {
        User user = this.getUserDataById(token);
        user.setVerify(true);
        userRepository.save(user);
        return user;
    }
}