package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.Book;
import com.bridgelabz.bookstorebackend.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService iBookService;

    @GetMapping("/hello")
    public String hello() {
        return "Welcome Yogesh !!!";
    }

    //--------------------------------- Add New Book Data ---------------------------------
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Add Book  Success", iBookService.addBook(bookDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Get Book Data By Id ---------------------------------

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("successfully record founded for given id: " + id, iBookService.getBookById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Get Book Data By Name ---------------------------------

    @GetMapping("/getBybookName/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable("bookName") String bookName) {
        ResponseDTO responseDTO = new ResponseDTO("successfully record founded for given book name: " + bookName, iBookService.findBookByName(bookName));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Get All Books Data ---------------------------------

    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        ResponseDTO responseDTO = new ResponseDTO("Getting all the record...", iBookService.getAllBooks());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Update Book Data ---------------------------------

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable int id, @Valid @RequestBody BookDTO bookDTO) {
        Book bookData = iBookService.updateBookData(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book Details Successfully", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Delete Book Data ---------------------------------

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("Delete Operation Successful", iBookService.deleteBookById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Sort Book Data By Name ---------------------------------

    @GetMapping("/sortByBookName")
    public ResponseEntity<ResponseDTO> sortBookByName() {
        List<Book> book = iBookService.sortBookByName();
        ResponseDTO responseDTO = new ResponseDTO("Get call for sorted data by book name successful", book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Sort Book Data By Price Low To High ---------------------------------

    @GetMapping("/sortByLowToHighPrice")
    public ResponseEntity<ResponseDTO> sortBookByPriceLowToHigh() {
        List<Book> book = iBookService.sortBookByPriceLowToHigh();
        ResponseDTO responseDTO = new ResponseDTO("Get call for sorted data by book price successful", book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Sort Book Data By Price High To Low---------------------------------

    @GetMapping("/sortByHighToLowPrice")
    public ResponseEntity<ResponseDTO> sortBookByPriceHighToLow() {
        List<Book> book = iBookService.sortBookByPriceHighToLow();
        ResponseDTO responseDTO = new ResponseDTO("Get call for sorted data by book price successful", book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Sort Book Data By Quantity ---------------------------------

    @GetMapping("/sortByBookQuantity")
    public ResponseEntity<ResponseDTO> sortBookByQuantity() {
        List<Book> book = iBookService.sortBookByQuantity();
        ResponseDTO responseDTO = new ResponseDTO("Get call for sorted data by book price successful", book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Update Book Quantity ---------------------------------

    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int id, @RequestBody BookDTO bookDTO) {
        Book bookData = iBookService.updateBookQuantity(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book Quantity Successfully", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Update Book Price ---------------------------------
    @PutMapping("/updatePrice/{id}")
    public ResponseEntity<ResponseDTO> updatePrice(@PathVariable int id, @RequestBody BookDTO bookDTO) {
        Book bookData = iBookService.updateBookPrice(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book Price Successfully", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}

