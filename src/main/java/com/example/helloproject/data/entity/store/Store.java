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
    private String hours;
    private String info;
    private String lastOrder;
    private boolean hide_yn;


    @Builder
    public Store(Long id, String name, String tel, String address, String hours, String info, String lastOrder, boolean hide_yn) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.hours = hours;
        this.info = info;
        this.lastOrder = lastOrder;
        this.hide_yn = hide_yn;

    }



    public void update(String name, String tel, String address, String hours, String info, String lastOrder, boolean hide_yn) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.hours = hours;
        this.info = info;
        this.lastOrder = lastOrder;
        this.hide_yn = hide_yn;


    }
}
