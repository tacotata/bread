package com.example.helloproject.data.repository.orders;

import com.example.helloproject.data.entity.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrdersRepository extends JpaRepository<Orders, Long> , QuerydslPredicateExecutor<Orders>, OrdersRepositoryCustom {

}
