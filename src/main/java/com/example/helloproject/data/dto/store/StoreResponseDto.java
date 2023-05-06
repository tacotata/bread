package com.example.helloproject.data.dto.store;

import com.example.helloproject.data.entity.store.Store;
import lombok.Getter;


@Getter
public class StoreResponseDto {
    private Long id;
    private String name;
    private String tel;
    private String address;
    private String info;
    private String lastOrder;
    private boolean hide_yn;
    private String open;
    private String close;
    private String startPickupTime;
    private String endPickupTime;
    private int reserveNum;



    public StoreResponseDto(Store entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.tel = entity.getTel();
        this.address = entity.getAddress();
        this.info = entity.getInfo();
        this.lastOrder = entity.getLastOrder();
        this.hide_yn = entity.isHide_yn();
        this.open = entity.getOpen();
        this.close = entity.getClose();
        this.startPickupTime = entity.getStartPickupTime();
        this.endPickupTime = entity.getEndPickupTime();
        this.reserveNum = entity.getReserveNum();
    }
}
