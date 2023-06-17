package com.bridgelabz.bookstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "order_id", nullable = false)
    private int orderId;
    @org.hibernate.annotations.ForeignKey(name = "none")
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    public List<Cart> cart;
    private int userid;
    private LocalDate orderDate;
    @ManyToMany
    public List<Book> bookList;
    private String address;
    private boolean cancel;

    public Order(int id, String address, List<Cart> cartList, List<Book> bookList, LocalDate now, int totalOrderQuantity, double totalOrderPrice, boolean cancel) {
        this.cart = cartList;
        this.bookList = bookList;
        this.userid = id;
        this.orderDate = now;
        this.address = address;
        this.cancel = cancel;
    }
}