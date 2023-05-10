package com.example.helloproject.data.entity.cart;

import com.example.helloproject.data.entity.BaseEntity;
import com.example.helloproject.data.entity.menu.Items;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CartItem  extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Items item;

    private int count;

    @Builder
    public CartItem(Cart cart, Items item, int count) {
        this.cart = cart;
        this.item = item;
        this.count = count;
    }
}
