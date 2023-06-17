package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.OrderDTO;
import com.bridgelabz.bookstorebackend.email.Email;
import com.bridgelabz.bookstorebackend.email.IEmailService;
import com.bridgelabz.bookstorebackend.exception.BookStoreException;
import com.bridgelabz.bookstorebackend.model.Book;
import com.bridgelabz.bookstorebackend.model.Cart;
import com.bridgelabz.bookstorebackend.model.Order;
import com.bridgelabz.bookstorebackend.model.User;
import com.bridgelabz.bookstorebackend.repository.BookRepository;
import com.bridgelabz.bookstorebackend.repository.CartRepository;
import com.bridgelabz.bookstorebackend.repository.OrderRepository;
import com.bridgelabz.bookstorebackend.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService implements IOrderService {
    @Autowired
    IBookService iBookService;
    @Autowired
    IUserService iUserService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    IEmailService iEmailService;
    List<Order> orderList = new ArrayList<>();

    //--------------------------------- place Order ---------------------------------
    @Override
    public Order placeOrder(OrderDTO orderDTO, String token) {
        int id = tokenUtil.decodeToken(token);
        List<Cart> cart = cartRepository.findByUserId(id);
        double totalOrderPrice = 0;
        int totalOrderQty = 0;
        List<Book> orderedBooks = new ArrayList<>();
        String address = "";
        for (int i = 0; i < cart.size(); i++) {
            totalOrderPrice += cart.get(i).getTotalPrice();
            totalOrderQty += cart.get(i).getQuantity();
            orderedBooks.add(cart.get(i).getBook());
        }
        User user = iUserService.getUserDataById(token);
//        if (orderDTO.getAddress() == null) {

        address = user.getAddress();
//        } else
//            address = orderDTO.getAddress();
        Order order = new Order(id, address, cart, orderedBooks, LocalDate.now(), totalOrderQty, totalOrderPrice, false);
        orderList.add(order);
        orderRepository.save(order);
        Email email = new Email(user.getEmail(), "Order Placed", "Successful");
        iEmailService.sendMail(email);
        for (int i = 0; i < cart.size(); i++) {
            Book book = cart.get(i).getBook();
            int updatedQty = this.updateBookQty(book.getBookQuantity(), cart.get(i).getQuantity());
            book.setBookQuantity(updatedQty);
            cartRepository.deleteById(cart.get(i).getCartId());
        }
        return order;
    }

    private int updateBookQty(int bookQuantity, double quantity) {
        double updatedQty = bookQuantity - quantity;
        if (updatedQty <= 0)
            return 0;
        else
            return (int) updatedQty;
    }

    //--------------------------------- Get Order Data By Id ---------------------------------
    @Override
    public Object getOrderById(String token) {
        int id = tokenUtil.decodeToken(token);
        return orderRepository.findById(id);
    }

    //--------------------------------- Get All Order Data ---------------------------------
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //--------------------------------- Cancel Order ---------------------------------
    @Override
    public void cancelOrder(int orderID) {
        Order order = this.getOrderById(orderID);
        if (order.isCancel() == false) {
            orderRepository.updateCancel(orderID);
            List<Book> book = order.getBookList();
            for (int j = 0; j < orderList.size(); j++) { //order ids
                if (orderList.get(j).getOrderId() == orderID) { //id is present or not
                    for (int i = 0; i < book.size(); i++) { // book size quantity in order
                        Book updateBook = (Book) iBookService.getBookById(book.get(i).getBookId()); //which to be update
                        for (int k = 0; k < book.size(); k++) {// cart
                            int orderedBookQty = (int) orderList.get(j).getCart().get(k).getQuantity(); //from cart quantity
                            int orderedBookId = orderList.get(j).getCart().get(k).getBook().getBookId(); //book id
                            int bookId = updateBook.getBookId();
                            if (orderedBookId == bookId) {
                                int updatedQty = orderedBookQty + updateBook.getBookQuantity();
                                updateBook.setBookQuantity(updatedQty);
                                bookRepository.save(book.get(i));
                            }
                        }
                    }
                }
            }
        } else throw new BookStoreException("Order is already canceled!");
    }

    @Override
    public Order getOrderById(int orderID) {
        return orderRepository.findById(orderID)
                .orElseThrow(() -> new BookStoreException("Order with id " + orderID + " not found!"));
    }
}
