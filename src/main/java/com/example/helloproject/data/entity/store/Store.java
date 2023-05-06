package com.example.helloproject.data.entity.store;

import com.example.helloproject.data.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @Builder
    public Store(Long id, String name, String tel, String address, String info, String lastOrder, boolean hide_yn, String open, String close, String startPickupTime, String endPickupTime, int reserveNum) {
        this.id = id;
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



    public void update(String name, String tel, String address, String info, String lastOrder, boolean hide_yn,  String open, String close, String startPickupTime, String endPickupTime, int reserveNum) {
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
