package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update BookStore_Backend.book_order set cancel=true where order_id=:id", nativeQuery = true)
    int updateCancel(int id);
}
