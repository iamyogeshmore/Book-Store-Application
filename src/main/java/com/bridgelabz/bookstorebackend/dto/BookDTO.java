package com.bridgelabz.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ElementCollection;

import com.bridgelabz.bookstorebackend.model.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor

public @ToString class BookDTO {
    public String bookName;
    public int price;
    public String authorName;
    @ElementCollection
    public Book book;
    public int bookQuantity;
    public String profilePic;
    public String bookDescription;
}