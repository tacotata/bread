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
    private String info;
    private String lastOrder;
    private boolean hide_yn;
    private String open;
    private String close;
    private String startPickupTime;
    private String endPickupTime;
    private int reserveNum;


    @Builder
    public StoreSaveRequestDto( String name, String tel, String address, String info, String lastOrder, boolean hide_yn, String open, String close, String startPickupTime, String endPickupTime, int reserveNum) {
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

    public Store toEntity(){
        return Store.builder().name(name).tel(tel).address(address).info(info).lastOrder(lastOrder).hide_yn(hide_yn).open(open).close(close).startPickupTime(startPickupTime).endPickupTime(endPickupTime).reserveNum(reserveNum).build();
    }
}



