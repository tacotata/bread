package com.example.helloproject.data.repository.cart;


import com.example.helloproject.data.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
