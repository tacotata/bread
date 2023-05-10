package com.example.helloproject.data.dto.cart;

import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.cart.CartItem;
import com.example.helloproject.data.entity.menu.Items;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemSaveRequestDto {
    private Cart cart;
    private Items item;
    private int count;

    @Builder
    public CartItemSaveRequestDto(Cart cart, Items item, int count) {
        this.cart = cart;
        this.item = item;
        this.count = count;
    }

    public CartItem toEntity(){
        return CartItem.builder().cart(cart).item(item).count(count).build();
    }
}



