package com.example.helloproject.data.entity.orders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    ORDER("ROLE_ORDER", "결제완료"),
    RECEIVED("ROLE_RECEIVED","수령"),
    NOTRECEIVED("ROLE_NOTRECEIVED","미수령");

    private final String key;
    private final String title;
}
