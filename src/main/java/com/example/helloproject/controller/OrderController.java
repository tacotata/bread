package com.example.helloproject.controller;


import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping(value = "/store-pick" , method = RequestMethod.GET)
    public String storePick(Model model, @LoginUser SessionUser user  ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/order/store-pick";
    }

    @RequestMapping(value = "/date-pick" , method = RequestMethod.GET)
    public String datePick(Model model, @LoginUser SessionUser user  ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/order/date-pick";
    }

    @RequestMapping(value = "/menu-pick" , method = RequestMethod.GET)
    public String menuPick(Model model, @LoginUser SessionUser user  ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/order/menu-pick";
    }

    @RequestMapping(value = "/menu-pick-detail" , method = RequestMethod.GET)
    public String menuPickDetail(Model model, @LoginUser SessionUser user  ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/order/menu-pick-detail";
    }

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String orderList(Model model, @LoginUser SessionUser user  ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/order/list";
    }


}
