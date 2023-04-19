package com.example.helloproject.config.auth;

import com.example.helloproject.config.auth.dto.OAuthAttributes;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.repository.user.UsersSnsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UsersSnsRepository usersSnsRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Users users = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(users));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(users.getRoleKey())),attributes.getAttributes(), attributes.getNameAttributeKey());

    }

    //홈페이지로 가입한 이메일로 sns 회원가입하면 어떻게 해야함????????????
    private Users saveOrUpdate(OAuthAttributes attributes){
        Users users = usersSnsRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture(), attributes.getMobile()))
                .orElse(attributes.toEntity());
        return usersSnsRepository.save(users);
    }

}
