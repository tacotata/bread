package com.example.helloproject.data.entity.menu;

import com.example.helloproject.data.dto.item.ItemFormDto;
import com.example.helloproject.data.entity.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Items extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String info;

    @Column
    private String allergy;

    @Column
    private String originInfo;

    @Column
    private int count;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemsType itemsType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemsStatus itemsStatus;


    @Builder
    public Items(Long id, String name, int price, String info , String allergy, String originInfo, int count, ItemsType itemsType, ItemsStatus itemsStatus) {
        this.id = id;
        this.name = name;      
        this.price = price;
        this.info = info;
        this.allergy = allergy;
        this.originInfo = originInfo;
        this.count = count;
        this.itemsType = itemsType;
        this.itemsStatus = itemsStatus;
    }


    public void updateItem(ItemFormDto itemFormDto){
        this.id = itemFormDto.getId();
        this.name = itemFormDto.getName();
        this.price = itemFormDto.getPrice();
        this.info = itemFormDto.getInfo();
        this.allergy = itemFormDto.getAllergy();
        this.originInfo = itemFormDto.getOriginInfo();
        this.count = itemFormDto.getCount();
        this.itemsType = itemFormDto.getItemsType();
        this.itemsStatus = itemFormDto.getItemsStatus();
    }

    public void updateItemStatus(String itemsStatus){
        this.itemsStatus = ItemsStatus.valueOf(itemsStatus);
    }


    public String getItemsStatusKey(){
        return this.itemsType.getKey();
    }
    public String getItemsTypeKey(){ return this.itemsStatus.getKey(); }
}
