package com.example.helloproject.data.entity.user;

import com.example.helloproject.data.dto.users.UsersDto;
import com.example.helloproject.data.entity.BaseEntity;
import com.example.helloproject.data.entity.store.Store;
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
    private String team;

    @Column
    private String teamTel;

    @Column
    private Long storeId;


    @Builder
    public Users(String password, String birthmonth, boolean promotionAgree, boolean privacyAgree , Long id, String name, String email, String picture, Role role, String birthday, String birthyear, String mobile, String snsType, String snsId ,  String team, String teamTel, Long storeId ) {
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

        this.team = team;
        this.teamTel = teamTel;


        this.storeId =storeId;
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

    public Users updateUserInfo(Role role, String name, String email, String mobile, String birthyear, String birthmonth, String birthday, boolean promotionAgree, String team, String teamTel, Long storeId) {
        this.role = role;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.birthyear = birthyear;
        this.birthmonth = birthmonth;
        this.birthday = birthday;
        this.promotionAgree = promotionAgree;
        this.team = team;
        this.teamTel = teamTel;
        this.storeId = storeId;
        return this;
    }

    public Users UpdatePwd(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
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
                .team(usersDto.getTeam())
                .teamTel(usersDto.getTeamTel())
                .storeId(usersDto.getStoreId())
                .build();
        return users;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
