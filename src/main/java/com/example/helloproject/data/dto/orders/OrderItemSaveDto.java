package com.example.helloproject.data.dto.orders;

import com.example.helloproject.data.entity.menu.Items;
import com.example.helloproject.data.entity.orders.OrderItem;
import com.example.helloproject.data.entity.orders.Orders;
import lombok.Builder;
import lombok.Getter;


@Getter
public class OrderItemSaveDto {

    private Long id;
    private Items item;
    private Orders orders;
    private int orderPrice;
    private int count;


    @Builder
    public OrderItemSaveDto(Long id, Items item, Orders orders, int orderPrice, int count) {
        this.id = id;
        this.item = item;
        this.orders = orders;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public OrderItem toEntity() {
        return OrderItem.builder()
                .id(id)
                .item(item)
                .orders(orders)
                .orderPrice(orderPrice)
                .count(count)
                .build();
    }
}
