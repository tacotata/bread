package com.example.helloproject.data.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreUpdateRequestDto {
    private Long id;
    private Long storeImg;
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

    @Builder
    public StoreUpdateRequestDto(Long id, Long storeImg, String name, String tel, String address, String info, String lastOrder, boolean hide_yn, String open, String close, String startPickupTime, String endPickupTime, int reserveNum) {
        this.id = id;
        this.storeImg =storeImg;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.info = info;
        this.lastOrder = lastOrder;
        this.hide_yn = hide_yn;
        this.open = open;
        this.close = close;
        this.startPickupTime = startPickupTime;
        this.endPickupTime = endPickupTime;
        this.reserveNum = reserveNum;
    }


}
