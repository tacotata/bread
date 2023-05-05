package com.example.helloproject.data.dto.store;

import com.example.helloproject.data.entity.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreSaveRequestDto {
    private String name;
    private String tel;
    private String address;
    private String hours;
    private String info;
    private String lastOrder;
    private boolean hide_yn;
    private String pickUpTime;


    @Builder
    public StoreSaveRequestDto( String name, String tel, String address, String hours, String info, String lastOrder, boolean hide_yn, String pickUpTime) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.hours = hours;
        this.info = info;
        this.lastOrder = lastOrder;
        this.hide_yn = hide_yn;
        this.pickUpTime = pickUpTime;
    }

    public Store toEntity(){
        return Store.builder().name(name).tel(tel).address(address).hours(hours).info(info).lastOrder(lastOrder).hide_yn(hide_yn).pickUpTime(pickUpTime).build();
    }
}



