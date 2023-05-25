package com.example.helloproject.data.entity.cs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CsType {
    PRAISE("ROLE_PRAISE", "칭찬"),
    PROPOSAL("ROLE_PROPOSAL","제안"),
    DISCONTENT("ROLE_DISCONTENT","불만"),
    ETC("ROLE_ETC","기타");

    private final String key;
    private final String title;
}
