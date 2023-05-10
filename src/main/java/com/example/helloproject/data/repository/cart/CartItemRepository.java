package com.example.helloproject.data.repository.cart;

import com.example.helloproject.data.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
