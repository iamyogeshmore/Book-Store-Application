package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //--------------------------------- User Login ---------------------------------
    @Query(value = "select * from user where email = :email AND password = :password", nativeQuery = true)
    User findByEmailIdAndPassword(String email, String password);

    //--------------------------------- Forgot Password ---------------------------------
    @Query(value = "select * from user where email=:email", nativeQuery = true)
    User forgotPassword(String email);

    @Query(value = "select * from user where id= :id", nativeQuery = true)
    Optional<User> findById(int id);

}