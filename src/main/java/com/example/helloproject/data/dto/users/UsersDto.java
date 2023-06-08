package com.example.helloproject.data.dto.users;

import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Role;
import com.example.helloproject.data.entity.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
public class UsersDto {


    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Length(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$" , message ="비밀번호는 최소 8글자~16자, 영문자 1개, 숫자 1개, 특수문자( @,$,!,%,*,?,& ) 1개가 포함되어야합니다.")
    private String password;


    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    private Role role;

    @Pattern(regexp="^[0-9]+$", message = "숫자만 입력가능합니다.")
    @Length(min=4, max=4, message = "4자를 입력해주세요. ex) 1990")
    private String birthyear;

    @Pattern(regexp="^[0-9]+$", message = "숫자만 입력가능합니다.")
    @Length(min=2, max=2, message = "2자를 입력해주세요. ex) 02")
    private String birthmonth;

    @Pattern(regexp="^[0-9]+$", message = "숫자만 입력가능합니다.")
    @Length(min=2, max=2, message = "2자를 입력해주세요. ex) 01")
    private String birthday;

    @Pattern(regexp="^01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})$",  message = "휴대폰번호를 확인해주세요.")
    private String mobile;

    private boolean promotionAgree;

    @AssertTrue(message = "개인정보 수집 및 이용 동의는 필수입니다.")
    private boolean privacyAgree;

    private String team;
    private String teamTel;
    private Long storeId;

    @Builder
    public UsersDto( String name, String password,String email, Role role, String birthday, String birthyear, String birthmonth, String mobile, boolean promotionAgree, boolean privacyAgree, String team, String teamTel, Long storeId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.birthday = birthday;
        this.birthyear = birthyear;
        this.birthmonth = birthmonth;
        this.mobile = mobile;
        this.promotionAgree = promotionAgree;
        this.privacyAgree = privacyAgree;
        this.team = team;
        this.teamTel = teamTel;
        this.storeId =storeId;
    }

    public Users toEntity(){
        return Users.builder().name(name).password(password).email(email).role(role).birthday(birthday).birthyear(birthyear).mobile(mobile).promotionAgree(promotionAgree).privacyAgree(privacyAgree).storeId(storeId).build();
    }

}
