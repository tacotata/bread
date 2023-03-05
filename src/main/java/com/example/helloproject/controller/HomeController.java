package com.example.helloproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class HomeController {


    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String home( ){
        return "/index";
    }

    @RequestMapping(value = "/brand" , method = RequestMethod.GET)
    public String brand( ){
        return "/brand";
    }

    @RequestMapping(value = "/contact" , method = RequestMethod.GET)
    public String contact( ){
        return "/contact";
    }


}
