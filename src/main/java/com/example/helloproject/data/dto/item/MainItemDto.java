package com.example.helloproject.data.dto.item;

import com.example.helloproject.data.entity.menu.ItemsType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MainItemDto {

    private Long id;
    private String name;
    private String info;
    private Integer price;
    private LocalDateTime regDate;
    private LocalDateTime updDate;
    private String fileName;
    private ItemsType itemsType;

    @QueryProjection
    public MainItemDto(Long id, String name, String info, Integer price, LocalDateTime regDate, LocalDateTime updDate,String fileName, ItemsType itemsType ){
        this.id = id;
        this.name = name;
        this.price = price;
        this.regDate = regDate;
        this.updDate = updDate;
        this.fileName = fileName;
        this.itemsType = itemsType;
    }

}
