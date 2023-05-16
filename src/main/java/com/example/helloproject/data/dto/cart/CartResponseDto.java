package com.example.helloproject.data.dto.cart;

import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CartResponseDto {
    private Long id;
    private Users users;
    private Store store;
    private String reservedDate;
    private String reservedTime;
    private LocalDateTime regDate;
    private LocalDateTime  updDate;

    public CartResponseDto(Cart entity){
        this.id = entity.getId();
        this.users = entity.getUsers();
        this.store = entity.getStore();
        this.reservedDate = entity.getReservedTime();
        this.reservedTime = entity.getReservedTime();
        this.regDate = entity.getRegDate();
        this.updDate = entity.getUpdDate();
    }
}
