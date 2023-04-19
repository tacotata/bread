package com.example.helloproject.data.dto.users;

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


    @Builder
    public UsersUpdateRequestDto( String name, String password,String email, Role role, String mobile, String birthyear, String birthmonth, String birthday, boolean promotionAgree) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.mobile = mobile;
        this.birthyear = birthyear;
        this.birthmonth = birthmonth;
        this.birthday = birthday;
        this.promotionAgree = promotionAgree;
    }


}
