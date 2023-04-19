package com.example.helloproject.data.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "고객"),
    ADMIN("ROLE_ADMIN","본사"),
    OWNER("ROLE_OWNER","점주");

    private final String key;
    private final String title;
}
