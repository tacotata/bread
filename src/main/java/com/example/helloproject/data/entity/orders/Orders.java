package com.example.helloproject.data.entity.orders;

import com.example.helloproject.data.entity.BaseEntity;
import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JoinColumn(name="order_id")
    private Long id;

    //회원 1명 여러번 주문
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String reservedDate;

    private String reservedTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="store_id")
    private Store store;

    //하나의 주문이  여러개의 주문 상품을 갖으므로 list 자료형 매핑
    @OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public String getOrderStatusKey(){
        return this.orderStatus.getKey();
    }

    @Builder
    public Orders(Long id, Users users, OrderStatus orderStatus, String reservedDate, String reservedTime, Store store, List<OrderItem> orderItems) {
        this.id = id;
        this.users = users;
        this.orderStatus = orderStatus;
        this.reservedDate = reservedDate;
        this.reservedTime = reservedTime;
        this.store = store;
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }

    public static Orders createOrders(Users users, List<OrderItem> orderItemList, Cart cart) {
        Orders orders = new Orders();
        orders.setUsers(users);
        orders.setReservedDate(cart.getReservedDate());
        orders.setReservedTime(cart.getReservedTime());
        orders.setStore(cart.getStore());

        for(OrderItem orderItem : orderItemList) {
            orders.addOrderItem(orderItem);
        }

        orders.setOrderStatus(OrderStatus.ORDER);

        return orders;
    }


}
