package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.exception.BookStoreException;
import com.bridgelabz.bookstorebackend.model.Book;
import com.bridgelabz.bookstorebackend.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService implements IBookService {
    @Autowired
    BookRepository bookRepository;

    public void isEmpty(List<Book> bookList) {
        if (bookList.isEmpty())
            throw new BookStoreException(" Book is empty!");
    }

    //--------------------------------- Add New Book Data ---------------------------------
    @Override
    public Object addBook(BookDTO bookDTO) {
        Book addData = new Book(bookDTO);
        return bookRepository.save(addData);
    }

    //--------------------------------- Get Book Data By Id ---------------------------------
    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookStoreException("Book with id " + id + " is not found!"));
    }

    //--------------------------------- Get Book Data By Name ---------------------------------
    @Override
    public Object findBookByName(String bookName) {
        List<Book> bookList = bookRepository.findBookByName(bookName);
        if (bookList.isEmpty()) {
            throw new BookStoreException(" Book with name " + bookName + " is not found!");
        }
        return bookList;
    }

    //--------------------------------- Get All Book Data ---------------------------------
    @Override
    public Object getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        isEmpty(bookList);
        return bookList;
    }

    //--------------------------------- Update Book Data ---------------------------------
    @Override
    public Book updateBookData(int id, BookDTO bookDTO) {
        Book updatedData = this.getBookById(id);
        updatedData.updateData(bookDTO);
        return bookRepository.save(updatedData);
    }

    //--------------------------------- Delete Book Data ---------------------------------
    @Override
    public Object deleteBookById(Integer id) {
        Optional<Book> bookDetails = bookRepository.findById(id);
        if (bookDetails.isPresent()) {
            bookRepository.deleteById(id);
            return "Book Id :" + id + " is deleted.";
        }
        throw new BookStoreException("Book id " + id + " is not found");
    }

    //--------------------------------- Sort Book Data By Name ---------------------------------
    @Override
    public List<Book> sortBookByName() {
        List<Book> bookList = bookRepository.findAllByOrderByBookName();
        isEmpty(bookList);
        return bookList;
    }

    //--------------------------------- Sort Book Data By Price Low To High ---------------------------------
    @Override
    public List<Book> sortBookByPriceLowToHigh() {
        List<Book> bookList = bookRepository.findAllByPriceLowToHigh();
        isEmpty(bookList);
        return bookList;
    }

    //--------------------------------- Sort Book Data By Price High To Low ---------------------------------
    @Override
    public List<Book> sortBookByPriceHighToLow() {
        List<Book> bookList = bookRepository.findAllByPriceHighToLow();
        isEmpty(bookList);
        return bookList;
    }

    //--------------------------------- Sort Book Data By Quantity ---------------------------------
    @Override
    public List<Book> sortBookByQuantity() {
        List<Book> bookList = bookRepository.findAllByOrderByBookQuantity();
        isEmpty(bookList);
        return bookList;
    }

    //--------------------------------- Update Book Quantity ---------------------------------
    @Override
    public Book updateBookQuantity(int id, BookDTO bookDTO) {
        Book updatedQuantity = this.getBookById(id);
        updatedQuantity.updateQuantity(bookDTO);
        return bookRepository.save(updatedQuantity);
    }

    //--------------------------------- Update Book Price ---------------------------------
    @Override
    public Book updateBookPrice(int id, BookDTO bookDTO) {
        Book updatedPrice = this.getBookById(id);
        updatedPrice.updatePrice(bookDTO);
        return bookRepository.save(updatedPrice);
    }
}
