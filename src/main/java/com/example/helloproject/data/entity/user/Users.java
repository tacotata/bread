package com.example.helloproject.data.entity.user;

import com.example.helloproject.data.dto.users.UsersDto;
import com.example.helloproject.data.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String birthday;
    @Column
    private String birthyear;
    @Column
    private String mobile;
    @Column
    private String snsType;
    @Column
    private String snsId;
    @Column
    private String password;
    @Column
    private String birthmonth;
    @Column
    private boolean promotionAgree;

    @Transient
    private boolean privacyAgree;

    @Column
    private String storeAddress;
    @Column
    private String storeName;
    @Column
    private String storeTel;
    @Column
    private String team;
    @Column
    private String teamTel;

    @Builder
    public Users(String password, String birthmonth, boolean promotionAgree, boolean privacyAgree , Long id, String name, String email, String picture, Role role, String birthday, String birthyear, String mobile, String snsType, String snsId ,String storeAddress, String storeName, String storeTel, String team, String teamTel ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.birthday = birthday;
        this.birthyear = birthyear;
        this.mobile = mobile;
        this.snsType = snsType;
        this.snsId = snsId;

        this.password = password;
        this.birthmonth = birthmonth;
        this.promotionAgree = promotionAgree;
        this.privacyAgree = privacyAgree;

        this.storeAddress = storeAddress;
        this.storeName = storeName;
        this.storeTel = storeTel;
        this.team = team;
        this.teamTel = teamTel;
    }

    public Users update (String name, String picture, String mobile){
        this.name  = name;
        this.picture = picture;
        this.mobile = mobile;
        return this;
    }

    public Users updateJoinSns ( String birthyear, String birthmonth, String birthday , String mobile, boolean promotionAgree){
        this.birthyear = birthyear;
        this.birthmonth = birthmonth;
        this.birthday = birthday;
        this.mobile = mobile;
        this.promotionAgree = promotionAgree;
        return this;
    }

    public Users updateUserInfo(Role role, String name, String mobile, String birthyear, String birthmonth, String birthday, boolean promotionAgree, String storeAddress, String storeName, String storeTel, String team, String teamTel) {
        this.role = role;
        this.name = name;
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
        return this;
    }

    public static Users createUsers(UsersDto usersDto, PasswordEncoder passwordEncoder) {
        Users users = Users.builder()
                .name(usersDto.getName())
                .password(passwordEncoder.encode(usersDto.getPassword()))  //암호화처리
                .email(usersDto.getEmail())
                .role(usersDto.getRole())
                .birthday(usersDto.getBirthday())
                .birthyear(usersDto.getBirthyear())
                .birthmonth(usersDto.getBirthmonth())
                .mobile(usersDto.getMobile())
                .promotionAgree(usersDto.isPromotionAgree())
                .storeAddress(usersDto.getStoreAddress())
                .storeName(usersDto.getStoreName())
                .storeTel(usersDto.getStoreTel())
                .team(usersDto.getTeam())
                .teamTel(usersDto.getTeamTel())
                .build();
        return users;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
