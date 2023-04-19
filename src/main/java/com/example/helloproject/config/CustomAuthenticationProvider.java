package com.example.helloproject.config;

import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Users users = usersRepository.findByEmail(username);

        UserDetails userDetails;

        if (users == null) {
            log.info("UsernameNotFoundException");
            throw new UsernameNotFoundException(username);
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(users.getRole().toString());
        userDetails = (UserDetails) new User(users.getEmail(),
                users.getPassword(), Arrays.asList(authority));

        if (!this.passwordEncoder.matches(password, userDetails.getPassword())) {
            log.info("BadCredentialsException");
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        httpSession.setAttribute("user", new SessionUser(users));
        log.info("로그인 성공 {}", users);
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
