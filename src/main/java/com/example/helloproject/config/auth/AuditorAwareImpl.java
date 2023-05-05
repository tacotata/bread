package com.example.helloproject.config.auth;

import com.example.helloproject.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuditorAwareImpl implements AuditorAware<Long> {
    private final HttpSession httpSession;

    @Override
    public Optional<Long> getCurrentAuditor() {
        log.info("ddddddddddd");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        try{
            log.info("user {}", user.getId());
            if(user == null)
                return null;

        }catch (Exception e){
            log.info(e.getMessage());

        }

        return Optional.ofNullable(user.getId());
    }
}
