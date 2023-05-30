//package com.example.helloproject.config.auth;
//
//import com.example.helloproject.config.CustomAuthenticationProvider;
//import com.example.helloproject.data.entity.user.Role;
//import com.example.helloproject.service.UsersService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfiguration {
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final UsersService usersService;
//    private final CustomAuthenticationProvider authenticationProvider;
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                .formLogin().
//                loginPage("/member/login").defaultSuccessUrl("/", true).failureUrl("/member/login/error").usernameParameter("email").and().logout().logoutSuccessUrl("/").logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")).and()
//                .authorizeRequests()
//                .antMatchers("/", "/static/**", "/css/**", "/images/**","/bootstrap.min.css","/animate.min.css","/aos.css","/owl.carousel.min.css","/owl.theme.default.min.css","/js/**", "/fonts/**","/scss/**","/member/login", "/member/new","/member/join", "/news/**", "/menu/**","/member/login/error").permitAll()
//                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
//                .antMatchers("/owner/**").hasRole(Role.OWNER.name())
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login().loginPage("/member/login")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login().defaultSuccessUrl("/", true)
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);
//        return http.build();
//    }
//
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
////            throws Exception {
////        return authenticationConfiguration.getAuthenticationManager();
////    }
//
//    @Bean
//    protected ProviderManagerBuilder providerManagerBuilder (AuthenticationManagerBuilder auth) throws Exception {
//
//        log.info("비밀번호");
//
//        return auth.authenticationProvider(authenticationProvider);
//       //auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());
//    }
//
//
//
//}
