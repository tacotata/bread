package com.example.helloproject.data.entity.orders;

import com.example.helloproject.data.entity.BaseEntity;
import com.example.helloproject.data.entity.menu.Items;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JoinColumn(name="orderItem_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name  ="item_id")
    private Items item;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders orders;

    private int orderPrice;

    private int count;

    @Builder
    public OrderItem(Long id, Items item, Orders orders, int orderPrice, int count) {
        this.id = id;
        this.item = item;
        this.orders = orders;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public int getTotalPrice(){
        return orderPrice * count;
    }
}
