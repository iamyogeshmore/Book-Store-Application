package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.OrderDTO;
import com.bridgelabz.bookstorebackend.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(OrderDTO orderDTO, String token);

    Object getOrderById(String token);

    List<Order> getAllOrders();

    void cancelOrder(int orderID);

    Order getOrderById(int orderID);
}
