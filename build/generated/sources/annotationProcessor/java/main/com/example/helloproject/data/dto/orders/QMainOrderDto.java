package com.example.helloproject.data.dto.orders;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.helloproject.data.dto.orders.QMainOrderDto is a Querydsl Projection type for MainOrderDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainOrderDto extends ConstructorExpression<MainOrderDto> {

    private static final long serialVersionUID = -349542378L;

    public QMainOrderDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<java.time.LocalDateTime> regDate, com.querydsl.core.types.Expression<String> reservedDate, com.querydsl.core.types.Expression<String> reservedTime, com.querydsl.core.types.Expression<? extends com.example.helloproject.data.entity.store.Store> store, com.querydsl.core.types.Expression<? extends com.example.helloproject.data.entity.user.Users> users, com.querydsl.core.types.Expression<com.example.helloproject.data.entity.orders.OrderStatus> orderStatus, com.querydsl.core.types.Expression<Integer> count, com.querydsl.core.types.Expression<Integer> orderPrice, com.querydsl.core.types.Expression<? extends com.example.helloproject.data.entity.menu.Items> item) {
        super(MainOrderDto.class, new Class<?>[]{long.class, java.time.LocalDateTime.class, String.class, String.class, com.example.helloproject.data.entity.store.Store.class, com.example.helloproject.data.entity.user.Users.class, com.example.helloproject.data.entity.orders.OrderStatus.class, int.class, int.class, com.example.helloproject.data.entity.menu.Items.class}, id, regDate, reservedDate, reservedTime, store, users, orderStatus, count, orderPrice, item);
    }

}

