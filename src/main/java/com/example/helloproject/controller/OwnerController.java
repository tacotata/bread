package com.example.helloproject.controller;

import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    @RequestMapping(value="/reservation-list")
    public String ownerReservationList(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/owner/reservation-list";
    }
}
