package com.example.helloproject.data.dto.orders;

import com.example.helloproject.data.entity.menu.Items;
import com.example.helloproject.data.entity.orders.OrderStatus;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Users;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MainOrderDto {

    private Long id;
    private LocalDateTime regDate;
    private String reservedDate;
    private String reservedTime;
    private String storeName;
    private Users users;
    private OrderStatus orderStatus;
    private int count;
    private int orderPrice;
    private String name;

    @QueryProjection
    public MainOrderDto(Long id, LocalDateTime regDate, String reservedDate, String reservedTime, Store store, Users users, OrderStatus orderStatus, Integer count, Integer orderPrice, Items item) {
        this.id = id;
        this.regDate = regDate;
        this.reservedDate = reservedDate;
        this.reservedTime = reservedTime;
        this.storeName = store.getName();
        this.users = users;
        this.orderStatus = orderStatus;
        this.count = count;
        this.orderPrice = orderPrice;
        this.name = item.getName();

    }

}


