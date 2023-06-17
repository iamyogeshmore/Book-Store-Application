package com.bridgelabz.bookstorebackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Integer cartId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private double quantity;
    public double totalPrice;

    public Cart(User user, Book book, int quantity, double totalPrice) {
        this.quantity = quantity;
        this.book = book;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }

    public double calculatePrice() {
        double totalPrice = this.getQuantity() * this.book.getPrice();
        return totalPrice;
    }

    public void totalPrice(double calculatePrice) {
       totalPrice = calculatePrice;
    }
}