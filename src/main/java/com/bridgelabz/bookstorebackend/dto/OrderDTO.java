package com.bridgelabz.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public @ToString class OrderDTO {
    public int userId;
    public String address;
}
