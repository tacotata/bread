package com.example.helloproject.data.entity.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemsStatus {
    SELL("ROLE_SELL", "판매중"),
    DISCON("ROLE_DISCON","판매중단");

    private final String key;
    private final String title;
}
