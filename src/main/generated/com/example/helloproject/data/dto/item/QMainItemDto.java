package com.example.helloproject.data.dto.item;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.helloproject.data.dto.item.QMainItemDto is a Querydsl Projection type for MainItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainItemDto extends ConstructorExpression<MainItemDto> {

    private static final long serialVersionUID = 2079239989L;

    public QMainItemDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> info, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<java.time.LocalDateTime> regDate, com.querydsl.core.types.Expression<java.time.LocalDateTime> updDate, com.querydsl.core.types.Expression<String> fileName, com.querydsl.core.types.Expression<com.example.helloproject.data.entity.menu.ItemsType> itemsType) {
        super(MainItemDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, String.class, com.example.helloproject.data.entity.menu.ItemsType.class}, id, name, info, price, regDate, updDate, fileName, itemsType);
    }

}

