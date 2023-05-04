package com.example.helloproject.data.entity.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemsType {
    DONUT("ROLE_DONUT", "도넛"),
    BAKERY("ROLE_BAKERY","베이커리"),
    CAKE("ROLE_CAKE","케이크");

    private final String key;
    private final String title;
}
