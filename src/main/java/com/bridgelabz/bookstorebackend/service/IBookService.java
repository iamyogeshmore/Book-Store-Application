package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.model.Book;

import java.util.List;

public interface IBookService {
    Object addBook(BookDTO bookDTO);

    Object getBookById(Integer id);

    Object findBookByName(String bookName);

    Object getAllBooks();

    Book updateBookData(int id, BookDTO bookDTO);

    Object deleteBookById(Integer id);

    List<Book> sortBookByName();

    List<Book> sortBookByPriceLowToHigh();

    List<Book> sortBookByPriceHighToLow();

    List<Book> sortBookByQuantity();

    Book updateBookQuantity(int id, BookDTO bookDTO);

    Book updateBookPrice(int id, BookDTO bookDTO);
}
