package com.example.helloproject.data.dto.orders;

import com.example.helloproject.data.entity.orders.OrderItem;
import com.example.helloproject.data.entity.orders.OrderStatus;
import com.example.helloproject.data.entity.orders.Orders;
import com.example.helloproject.data.entity.user.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrdersSaveDto {

    private Long id;
    private Users users;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems = new ArrayList<>();



    @Builder
    public OrdersSaveDto(Long id, Users users, OrderStatus orderStatus, List<OrderItem> orderItems) {
        this.id = id;
        this.users = users;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    public Orders toEntity() {
        return Orders.builder()
                .id(id)
                .users(users)
                .orderStatus(OrderStatus.ORDER)
                .orderItems(orderItems)
                .build();
    }
}
