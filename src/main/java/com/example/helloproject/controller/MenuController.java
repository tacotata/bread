package com.example.helloproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {


    @RequestMapping(value = "/menu" , method = RequestMethod.GET)
    public String menu( ){
        return "/menus/menu";
    }

    @RequestMapping(value = "/menu-detail" , method = RequestMethod.GET)
    public String menuDetail( ){
        return "/menus/detail";
    }
}
