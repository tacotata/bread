package com.example.helloproject.controller;

import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {


    @RequestMapping(value = "/menu" , method = RequestMethod.GET)
    public String menu(Model model, @LoginUser SessionUser user ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/menus/menu";
    }

    @RequestMapping(value = "/menu-detail" , method = RequestMethod.GET)
    public String menuDetail(Model model, @LoginUser SessionUser user ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/menus/detail";
    }
}
