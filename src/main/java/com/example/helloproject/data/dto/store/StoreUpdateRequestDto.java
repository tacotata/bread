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
    private String hours;
    private String info;
    private String lastOrder;
    private boolean hide_yn;
    private String pickUpTime;

    @Builder
    public StoreUpdateRequestDto(Long id, Long storeImg, String name, String tel, String address, String hours, String info, String lastOrder, boolean hide_yn, String pickUpTime) {
        this.id = id;
        this.storeImg =storeImg;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.hours = hours;
        this.info = info;
        this.lastOrder = lastOrder;
        this.hide_yn = hide_yn;
        this.pickUpTime =pickUpTime;
    }


}
