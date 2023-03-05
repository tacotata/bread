package com.example.helloproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreController {

    @RequestMapping(value="/stores")
    public String stores(){
        return "/store/stores";
    }

    @RequestMapping(value="/store-detail")
    public String storeDetail(){
        return "/store/store-detail";
    }
}
