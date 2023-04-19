package com.example.helloproject.data.dto.users;

import com.example.helloproject.data.entity.user.Role;
import com.example.helloproject.data.entity.user.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UsersResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String picture;
    private Role role;
    private String birthmonth;
    private String birthday;
    private String birthyear;
    private String mobile;
    private String snsType;
  //  private String snsId;
    private boolean promotionAgree;
    private LocalDateTime regDate;
    private LocalDateTime  updDate;


    public UsersResponseDto(Users entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.picture = entity.getPicture();
        this.role = entity.getRole();
        this.birthyear = entity.getBirthyear();
        this.birthmonth = entity.getBirthmonth();
        this.birthday = entity.getBirthday();
        this.mobile = entity.getMobile();
        this.snsType = entity.getSnsType();
        this.promotionAgree = entity.isPromotionAgree();
        this.regDate = entity.getRegDate();
        this.updDate = entity.getUpdDate();
    }
}
