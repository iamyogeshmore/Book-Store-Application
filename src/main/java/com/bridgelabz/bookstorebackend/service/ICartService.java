package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.CartDTO;
import com.bridgelabz.bookstorebackend.model.Cart;

public interface ICartService {
    Cart addCart(CartDTO cartDTO, String token);

    Object getCartById(String token);

    Object getCartByCartId(int cartId);

    Object getAllCartData();

    Object deleteCartById(int id, String token);

    Object deleteAllCartData();

    Cart updateCartItems(String token, CartDTO cartDTO, int id);
}
