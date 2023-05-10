package com.example.helloproject.data.dto.cart;

import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartSaveRequestDto {
    private Users users;
    private Store store;
    private String reservedDate;
    private String reservedTime;

    @Builder
    public CartSaveRequestDto(Users users, Store store, String reservedDate, String reservedTime) {
        this.users = users;
        this.store = store;
        this.reservedDate = reservedDate;
        this.reservedTime = reservedTime;
    }

    public Cart toEntity(){
        return Cart.builder().users(users).store(store).reservedDate(reservedDate).reservedTime(reservedTime).build();
    }
}



