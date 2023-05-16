package com.example.helloproject.data.dto.cart;

import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.cart.CartItem;
import com.example.helloproject.data.entity.menu.Items;

import lombok.Getter;


@Getter
public class CartItemResponseDto {
    private Long id;
    private Cart cart;
    private Items item;
    private int count;

    public CartItemResponseDto(CartItem entity){
        this.id = entity.getId();
        this.cart = entity.getCart();
        this.item = entity.getItem();
        this.count = entity.getCount();
    }
}
