package com.example.helloproject.config.auth.dto;

import com.example.helloproject.data.entity.user.Role;
import com.example.helloproject.data.entity.user.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private String birthyear;
    private String birthmonth;
    private String birthday;
    private String mobile;
    private String snsType;
    private Role role;

    public SessionUser(Users users) {
        this.id = users.getId();
        this.name = users.getName();
        this.email = users.getEmail();
        this.picture = users.getPicture();
        this.birthyear = users.getBirthyear();
        this.birthmonth = users.getBirthmonth();
        this.birthday = users.getBirthday();
        this.mobile = users.getMobile();
        this.snsType = users.getSnsType();
        this.role = users.getRole();
    }

}
