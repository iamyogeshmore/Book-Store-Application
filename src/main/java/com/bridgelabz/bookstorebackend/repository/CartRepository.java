package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

//    @Query(value = " SELECT * FROM  Bookstore_SpringBoot.cart WHERE user_id = :user_id",nativeQuery = true)
//    List<Cart> findByUserId(int user_id);
//
//    @Query(value = "SELECT * FROM BookStore.cart where user_id = :user_id AND book_id = :book_id",nativeQuery = true)
//    Cart findByUserIdBookId(int user_id,int book_id);

    @Query(value = " SELECT * FROM BookStore_Backend.cart WHERE user_id = :user_id",nativeQuery = true)
    List<Cart> findByUserId(int user_id);

    @Query(value = " SELECT * FROM BookStore_Backend.cart WHERE user_id = :user_id and book_id = :book_id",nativeQuery = true)
    Cart findByUserIdBookId(int user_id, int book_id);


    @Query(value = " SELECT * FROM BookStore_Backend.cart WHERE user_id = :id",nativeQuery = true)
    List<Cart> findByUserId(Long id);
}
