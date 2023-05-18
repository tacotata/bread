package com.example.helloproject.data.dto.orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long itemId;
    private Long cartItemId;
    private int count;
}
