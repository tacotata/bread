package com.example.helloproject.data.repository.orders;

import com.example.helloproject.data.entity.orders.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
