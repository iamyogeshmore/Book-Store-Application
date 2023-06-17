package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    //--------------------------------- Get Book Data By Name ---------------------------------
    @Query(value = "SELECT * FROM  book WHERE book_name = :book_name", nativeQuery = true)
    List<Book> findBookByName(@Param("book_name") String name);

    //--------------------------------- Sort Book Data By Name ---------------------------------
    @Query(value = "select * from book order by book_name asc ", nativeQuery = true)
    List<Book> findAllByOrderByBookName();

    //--------------------------------- Sort Book Data By Quantity ---------------------------------
    @Query(value = "select * from book order by book_quantity asc ", nativeQuery = true)
    List<Book> findAllByOrderByBookQuantity();

    //--------------------------------- Sort Book Data By Price Low To High ---------------------------------
    @Query(value = "select * from book order by price asc ", nativeQuery = true)
    List<Book> findAllByPriceLowToHigh();

    //--------------------------------- Sort Book Data By Price High To Low ---------------------------------
    @Query(value = "select * from book order by price desc ", nativeQuery = true)
    List<Book> findAllByPriceHighToLow();

    @Query(value = "SELECT * FROM  book WHERE book_id = :book_id", nativeQuery = true)
    Optional<Book> getBookById(int book_id);
}
