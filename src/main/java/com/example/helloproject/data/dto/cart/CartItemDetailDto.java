package com.example.helloproject.data.dto.cart;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CartItemDetailDto {

    private Long cartItemId;
    private Long cartId;
    private String name;
    private int price;
    private int count;
    private String fileName;
    private LocalDateTime updDate;


    public CartItemDetailDto(Long cartItemId, Long cartId, String name, int price, int count, String fileName, LocalDateTime updDate ){
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.name = name;
        this.price = price;
        this.count = count;
        this.fileName = fileName;
        this.updDate = updDate;
    }

}
