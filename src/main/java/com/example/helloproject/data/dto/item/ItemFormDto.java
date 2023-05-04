package com.example.helloproject.data.dto.item;

import com.example.helloproject.data.entity.menu.ItemsStatus;
import com.example.helloproject.data.entity.menu.ItemsType;
import com.example.helloproject.data.entity.menu.Items;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ItemFormDto {

     private Long id;

     @NotBlank(message = "상품명은 필수 입력 값입니다.")
     private String name;

     @NotBlank(message = "상품 설명은 필수 입력 값입니다.")
     private String info;

     @NotNull(message = "가격은 필수 입력 값입니다.")
     private int price;

     @NotBlank(message = "알러지 유발 요인은 필수 입력 값입다.")
     private String allergy;

     @NotBlank(message = "원산지 정보는 필수 입력 값입니다.")
     private String originInfo;

     private int count;

     private LocalDateTime regDate;

     private LocalDateTime updDate;

     private ItemsType itemsType;

     private ItemsStatus itemsStatus;

     private List<ItemFileSaveDto> itemFileSaveDtoList = new ArrayList<>();
     private List<ItemsFileResponseDto> itemsFileResponseDtoList = new ArrayList<>();

     private List<Long> itemFileIds = new ArrayList<>();
     private Long itemsFileId ;

    @Builder
    public ItemFormDto (Items entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.info = entity.getInfo();
        this.allergy = entity.getAllergy();
        this.originInfo = entity.getOriginInfo();
        this.count = entity.getCount();
        this.regDate = entity.getRegDate();
        this.updDate = entity.getUpdDate();
        this.itemsType = entity.getItemsType();
        this.itemsStatus = entity.getItemsStatus();
    }

    public Items toEntity(){
         return Items.builder().name(name).price(price).info(info).allergy(allergy).originInfo(originInfo).count(count).itemsType(itemsType).itemsStatus(itemsStatus).build();
     }


}
