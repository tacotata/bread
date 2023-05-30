package com.example.helloproject.data.dto.users;

import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersUpdateRequestDto {
    private Role role;
    private String name;
    private String password;
    private String email;
    private String mobile;
    private String birthyear;
    private String birthmonth;
    private String birthday;
    private boolean promotionAgree;
    private String storeAddress;
    private String storeName;
    private String storeTel;
    private String team;
    private String teamTel;
    private Long storeId;

    @Builder
    public UsersUpdateRequestDto(Role role, String name, String password, String email, String mobile, String birthyear, String birthmonth, String birthday, boolean promotionAgree, String storeAddress, String storeName, String storeTel, String team, String teamTel, Long storeId) {
        this.role = role;
        this.name = name;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.birthyear = birthyear;
        this.birthmonth = birthmonth;
        this.birthday = birthday;
        this.promotionAgree = promotionAgree;
        this.storeAddress = storeAddress;
        this.storeName = storeName;
        this.storeTel = storeTel;
        this.team = team;
        this.teamTel = teamTel;
        this.storeId = storeId;
    }
}
