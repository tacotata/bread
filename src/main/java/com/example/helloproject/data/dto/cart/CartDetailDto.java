package com.example.helloproject.data.dto.cart;

import lombok.Getter;

@Getter
public class CartDetailDto {

    private Long cartId;
    private String reservedDate;
    private String reservedTime;
    private Long storeId;

    private String name;


    public CartDetailDto(Long cartId, String reservedDate, String reservedTime, Long storeId, String name ){
        this.cartId = cartId;
        this.reservedDate = reservedDate;
        this.reservedTime = reservedTime;
        this.storeId = storeId;
        this.name = name;

    }

}
