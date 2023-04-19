package com.example.helloproject.config.auth.dto;

import com.example.helloproject.data.entity.user.Role;
import com.example.helloproject.data.entity.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
public class OAuthAttributes {
    private Map<String , Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String birthday;
    private String birthyear;
    private String birthmonth;
    private String mobile;
    private String snsType;
    private String snsId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String birthday, String birthyear, String birthmonth, String mobile, String snsType, String snsId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.birthday = birthday;
        this.birthyear = birthyear;
        this.birthmonth = birthmonth;
        this.mobile = mobile;
        this.snsType= snsType;
        this.snsId = snsId;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response  = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .birthday(((String) response.get("birthmonth")).substring(3,5))
                .birthmonth(((String) response.get("birthday")).substring(0,2))
                .birthyear((String) response.get("birthyear"))
                .mobile((String) response.get("mobile"))
                .snsType("Naver")
                .snsId((String) response.get("id"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();

    }


    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .snsType("Google")
                .snsId((String) attributes.get("sub"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();

    }

    public Users toEntity(){
        return Users.builder().name(name).email(email).picture(picture).role(Role.GUEST).birthday(birthday).birthyear(birthyear).mobile(mobile).snsId(snsId).snsType(snsType).build();
    }
}
