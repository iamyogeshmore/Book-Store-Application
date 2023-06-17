package com.bridgelabz.bookstorebackend.model;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Column(name = "bookId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int bookId;
    public String bookName;
    public String authorName;
    public int bookQuantity;
    public int price;
    public String profilePic;
    public String bookDescription;

    public Book(BookDTO bookDTO) {
        this.updateData(bookDTO);
    }

    public void updateData(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.authorName = bookDTO.authorName;
        this.bookQuantity = bookDTO.bookQuantity;
        this.price = bookDTO.price;
        this.profilePic = bookDTO.profilePic;
        this.bookDescription = bookDTO.bookDescription;
    }

    public void updateQuantity(BookDTO bookDTO) {
        this.bookQuantity = bookDTO.bookQuantity;
    }

    public void updatePrice(BookDTO bookDTO) {
        this.price = bookDTO.price;
    }
}