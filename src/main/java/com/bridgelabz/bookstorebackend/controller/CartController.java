package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.CartDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.Cart;
import com.bridgelabz.bookstorebackend.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService iCartService;

    //--------------------------------- Add New Cart Data ---------------------------------
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addCart(@RequestBody CartDTO cartDTO, @RequestParam String token) {
        Cart cart = iCartService.addCart(cartDTO , token);
        ResponseDTO responseDTO = new ResponseDTO("Item added in Cart Successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Get Cart Data By Token Id ---------------------------------
    @GetMapping("/GetByToken")
    public ResponseEntity<ResponseDTO> getCartById(@RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("All The Cart Items Bellow:", iCartService.getCartById(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Get Cart Data By Cart Id ---------------------------------
    @GetMapping("/GetById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartByCartId(@PathVariable("cartId") int cartId) {
        ResponseDTO responseDTO = new ResponseDTO("All The Cart Items Bellow:", iCartService.getCartByCartId(cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Get All Cart Data ---------------------------------
    @GetMapping("/GetAllCartData")
    public ResponseEntity<ResponseDTO> getAllCartData() {
        ResponseDTO responseDTO = new ResponseDTO("All The Cart Items Bellow:", iCartService.getAllCartData());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Delete Cart Data By Token Id ---------------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCartById(@RequestParam String token, @PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Delete Operation Successful", iCartService.deleteCartById(id , token ));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Delete All Cart Data ---------------------------------
    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseDTO> deleteAllCartData() {
        ResponseDTO responseDTO = new ResponseDTO("Delete Operation Successful", iCartService.deleteAllCartData());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Update Cart Quantity By Token ---------------------------------
    @PutMapping("/updateCartItems/{id}")
    public ResponseEntity<ResponseDTO> updateCartItems(@RequestParam String token,@RequestBody CartDTO cartDTO,@PathVariable int id) {
        Cart cartItems = iCartService.updateCartItems(token,cartDTO,id);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book Quantity Successfully", cartItems);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}