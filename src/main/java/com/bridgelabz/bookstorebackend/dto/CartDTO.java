package com.bridgelabz.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
public @Data class CartDTO {
    private int book_Id;
    @NotNull(message = "Quantity cannot be null!")
    public int quantity;
}
