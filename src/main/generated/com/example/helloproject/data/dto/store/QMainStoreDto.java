package com.example.helloproject.data.dto.store;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.helloproject.data.dto.store.QMainStoreDto is a Querydsl Projection type for MainStoreDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainStoreDto extends ConstructorExpression<MainStoreDto> {

    private static final long serialVersionUID = 1095129793L;

    public QMainStoreDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<java.time.LocalDateTime> updDate, com.querydsl.core.types.Expression<String> fileName) {
        super(MainStoreDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, String.class}, id, name, address, updDate, fileName);
    }

}

