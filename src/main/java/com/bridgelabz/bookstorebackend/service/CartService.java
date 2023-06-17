package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.CartDTO;
import com.bridgelabz.bookstorebackend.exception.BookStoreException;
import com.bridgelabz.bookstorebackend.model.Book;
import com.bridgelabz.bookstorebackend.model.Cart;
import com.bridgelabz.bookstorebackend.model.User;
import com.bridgelabz.bookstorebackend.repository.BookRepository;
import com.bridgelabz.bookstorebackend.repository.CartRepository;
import com.bridgelabz.bookstorebackend.repository.UserRepository;
import com.bridgelabz.bookstorebackend.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CartService implements ICartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    IUserService iUserService;

    public void isEmpty(List<Cart> cartList) {
        if (cartList.isEmpty())
            throw new BookStoreException(" Cart is empty!");
    }

    //--------------------------------- Add New Cart Data ---------------------------------
    @Override
    public Cart addCart(CartDTO cartDTO, String token) {
        int id = tokenUtil.decodeToken(token);
        Optional<Book> book = bookRepository.getBookById(cartDTO.getBook_Id());
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && book.isPresent()) {
            if (cartDTO.quantity <= book.get().getBookQuantity()) {
                Cart cart = cartRepository.findByUserIdBookId(user.get().getId(), cartDTO.getBook_Id());
                if (cart != null) {
                    Cart cartData = updateCart(cart.getCartId(), cartDTO);
                    return cartData;
                } else {
                    double totalPrice = calculateTotalPrice(cartDTO.getQuantity(), book.get().getPrice());
                    cart = new Cart(user.get(), book.get(), cartDTO.getQuantity(), totalPrice);
                    return cartRepository.save(cart);
                }
            } else throw new BookStoreException("Books Quantity Is Not Enough!");
        } else throw new BookStoreException("Books Quantity Is Not Enough!");
    }

    private double calculateTotalPrice(int quantity, int bookPrice) {
        return quantity * bookPrice;
    }

    private Cart updateCart(int id, CartDTO cartDTO) {
        if (cartRepository.findById(id).isPresent()) {
            Optional<Cart> cartDetails = cartRepository.findById(id);
            cartDetails.get().setQuantity(cartDetails.get().getQuantity() + cartDTO.getQuantity());
            cartDetails.get().totalPrice(cartDetails.get().calculatePrice());
            return cartRepository.save(cartDetails.get());
        } else {
            throw new BookStoreException("Record not available of provided id");
        }
    }

    //--------------------------------- Get Cart Data By Token Id ---------------------------------
    @Override
    public List<Cart> getCartById(String token) {
        int id = tokenUtil.decodeToken(token);
        return cartRepository.findByUserId(id);
    }

    //--------------------------------- Get Cart Data By Cart Id ---------------------------------
    @Override
    public Cart getCartByCartId(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new BookStoreException("Cart item with id " + cartId + " is not found!"));
    }

    //--------------------------------- Get All Cart Data ---------------------------------
    @Override
    public List<Cart> getAllCartData() {
        List<Cart> cartList = cartRepository.findAll();
        isEmpty(cartList);
        return cartList;
    }

    //--------------------------------- Delete Cart Data By Cart Id ---------------------------------
    @Override
    public String deleteCartById(int id, String token) {
        User user = iUserService.getUserDataById(token);
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent() && user != null) {
            cartRepository.delete(cart.get());
            return "Record is deleted with id ";
        }
        throw (new BookStoreException("Record not Found"));
    }

    //--------------------------------- Delete All Cart Data ---------------------------------
    @Override
    public Object deleteAllCartData() {
        cartRepository.deleteAll();
        return "All Cart Data Deleted Successfully.";
    }

    //--------------------------------- Update Cart Quantity ---------------------------------
    @Override
    public Cart updateCartItems(String token, CartDTO cartDTO, int id) {
        User userData = iUserService.getUserDataById((token));
        if (cartRepository.findById(id).isPresent() && userData != null) {
            Cart cart = cartRepository.findById(id).get();
            cart.setQuantity(cartDTO.quantity);
            cart.setTotalPrice(cart.getQuantity() * cart.getBook().getPrice());
            return cartRepository.save(cart);
        } else throw new BookStoreException("No book found with the given id or you are not an admin user.");
    }
}