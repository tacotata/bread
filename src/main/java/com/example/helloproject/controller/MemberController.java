package com.example.helloproject.controller;

import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.users.UsersDto;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/join")
    public String join(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        model.addAttribute("usersDto", new UsersDto());
        return "/member/join";
    }


    @PostMapping("/join")
    public String usersJoin( @Valid  @ModelAttribute("usersDto") UsersDto usersDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return"/member/join";
        }
        try {
            Users users = Users.createUsers(usersDto, passwordEncoder);
            usersService.saveUser(users);
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/join";
        }
        return "/member/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "/member/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return"/member/login";
    }

    @GetMapping("/mypage")
    public String mypage(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/member/mypage";
    }

    @GetMapping("/modify-before")
    public String modifyBefore(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/member/modify-before";
    }


    @GetMapping("/modify")
    public String modify(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/member/modify";
    }

/*
    @RequestMapping(value = "/pw-search" , method = RequestMethod.GET)
    public String pwSearch( ){
        return "/member/pw-search";
    }

    @RequestMapping(value = "/id-search" , method = RequestMethod.GET)
    public String idSearch( ){
        return "/member/id-search";
    }
*/


}
