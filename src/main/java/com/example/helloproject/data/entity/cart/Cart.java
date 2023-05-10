package com.example.helloproject.data.entity.cart;

import com.example.helloproject.data.dto.item.ItemFormDto;
import com.example.helloproject.data.entity.BaseEntity;
import com.example.helloproject.data.entity.menu.ItemsStatus;
import com.example.helloproject.data.entity.menu.ItemsType;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="store_id")
    private Store store;

    private String reservedDate;

    private String reservedTime;


    @Builder
    public Cart(Long id, Users users, Store store, String reservedDate, String reservedTime) {
        this.id = id;
        this.users = users;
        this.store = store;
        this.reservedDate = reservedDate;
        this.reservedTime = reservedTime;
    }

}
