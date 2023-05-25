package com.example.helloproject.config.auth;

import com.example.helloproject.config.CustomAuthenticationProvider;
import com.example.helloproject.data.entity.user.Role;
import com.example.helloproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    //private final UsersService usersService;
    private final CustomAuthenticationProvider authenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin().
                loginPage("/member/login").defaultSuccessUrl("/", true).failureUrl("/member/login/error").usernameParameter("email").and().logout().logoutSuccessUrl("/").logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")).and()
                .authorizeRequests()
                .antMatchers("/", "/index", "/static/**", "/css/**", "/images/**","/bootstrap.min.css","/animate.min.css","/aos.css","/owl.carousel.min.css","/owl.theme.default.min.css","/js/**", "/fonts/**","/scss/**","/member/login", "/member/new","/member/join", "/news/**", "/items", "/item/**", "/stores/**","/brand" ,"/member/login/error", "/upload/**", "/member/id-search", "/member/pw-search", "/member/api/v1/id-search", "/member/api/v1/pw-search").permitAll()
                .antMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/owner/**").hasAuthority(Role.OWNER.name())
                .anyRequest().authenticated()
                .and()
                .oauth2Login().loginPage("/member/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login().defaultSuccessUrl("/", true)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
       //auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());
    }
}
