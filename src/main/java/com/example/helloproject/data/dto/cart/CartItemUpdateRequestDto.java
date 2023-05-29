package com.example.helloproject.data.dto.cart;

import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.menu.Items;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemUpdateRequestDto {
    private Cart cart;
    private Items item;
    private int count;

    @Builder
    public CartItemUpdateRequestDto(Long id, Cart cart, Items item, int count) {
        this.cart = cart;
        this.item = item;
        this.count = count;
    }
}
