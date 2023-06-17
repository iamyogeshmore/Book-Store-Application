package com.bridgelabz.bookstorebackend.controller;

import com.bridgelabz.bookstorebackend.dto.OrderDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.Order;
import com.bridgelabz.bookstorebackend.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    //--------------------------------- place Order ---------------------------------
    @PostMapping("/placeOrder/{token}")
    public ResponseEntity<ResponseDTO> placeOrder(@Valid @RequestBody OrderDTO orderDTO, @PathVariable String token) {
        Order order = iOrderService.placeOrder(orderDTO, token);
        ResponseDTO responseDTO = new ResponseDTO("Order Placed Sucessfully", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Get Order Data By Id ---------------------------------

    @GetMapping("/getOrderById/{token}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Record found successfully", iOrderService.getOrderById(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Get All Order Data ---------------------------------

    @GetMapping("/getAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        ResponseDTO responseDTO = new ResponseDTO("Getting all the record..", iOrderService.getAllOrders());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Cancel Order ---------------------------------

    @PutMapping("/cancelOrder/{orderID}")
    public ResponseEntity<ResponseDTO> deleteOrderItem(@PathVariable int orderID) {
        iOrderService.cancelOrder(orderID);
        ResponseDTO responseDTO = new ResponseDTO("Order Canceled successfully !", "Order Canceled " + orderID);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
}
