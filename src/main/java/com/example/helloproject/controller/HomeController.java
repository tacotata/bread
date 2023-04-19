package com.example.helloproject.controller;

import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.users.UsersResponseDto;
import com.example.helloproject.data.dto.users.UsersUpdateRequestDto;
import com.example.helloproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UsersService usersService;

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionUser user){
        if(user != null ){
            UsersResponseDto member = usersService.findById(user.getId());
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
           if("Google".equals(member.getSnsType())  && member.getBirthday() == null ){
                model.addAttribute("snsType", member.getSnsType());
                model.addAttribute("id", member.getId());
            }
           //네이버 가입자는 promotionAgree 어떻게 보여주지????
            log.info("user : {}", user.getName());
        }
        return "/index";
    }

    @PutMapping("/sns-join/{id}")
    public String snsUsersUpdate(@PathVariable Long id ,@RequestBody UsersUpdateRequestDto usersUpdateRequestDto,  @LoginUser SessionUser user) {
        try {
           Long resultId =  usersService.updateJoinSns(id, usersUpdateRequestDto);
           log.info("SNS 회원가입 완료 아이디 : {} ", resultId);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "/index";
    }

    @GetMapping("/brand")
    public String brand(Model model, @LoginUser SessionUser user ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/brand";
    }

    @GetMapping("/contact")
    public String contact(Model model, @LoginUser SessionUser user ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/contact";
    }


}
