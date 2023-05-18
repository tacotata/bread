package com.example.helloproject.data.dto.cart;

import lombok.Getter;

import java.util.List;

@Getter
public class CartOrderDto {
    private Long cartItemId;
    private List<CartOrderDto> cartOrderDtoList;
}
