package com.example.helloproject.controller;


import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreController {

    @RequestMapping(value="/stores")
    public String stores(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/store/stores";
    }

    @RequestMapping(value="/store-detail")
    public String storeDetail(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/store/store-detail";
    }
}
