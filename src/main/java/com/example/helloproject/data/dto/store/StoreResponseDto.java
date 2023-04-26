package com.example.helloproject.data.dto.store;

import com.example.helloproject.data.entity.store.Store;
import lombok.Getter;


@Getter
public class StoreResponseDto {
    private Long id;
    private String name;
    private String tel;
    private String address;
    private String hours;
    private String info;
    private String lastOrder;
    private boolean hide_yn;


    public StoreResponseDto(Store entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.tel = entity.getTel();
        this.address = entity.getAddress();
        this.hours = entity.getHours();
        this.info = entity.getInfo();
        this.lastOrder = entity.getLastOrder();
        this.hide_yn = entity.isHide_yn();

    }
}
