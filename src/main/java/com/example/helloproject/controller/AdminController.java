package com.example.helloproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping(value = "/notice/registration" , method = RequestMethod.GET)
    public String adminNoticeRegi( ){
        return "/admin/notice/registration";
    }

    @RequestMapping(value = "/notice/modify" , method = RequestMethod.GET)
    public String adminNoticeModify( ){
        return "/admin/notice/modify";
    }


    @RequestMapping(value = "/menu/registration" , method = RequestMethod.GET)
    public String adminMenuRegi( ){
        return "/admin/menu/registration";
    }


    @RequestMapping(value = "/menu/modify" , method = RequestMethod.GET)
    public String adminMenuModify( ){
        return "/admin/menu/modify";
    }


    @RequestMapping(value = "/store/registration" , method = RequestMethod.GET)
    public String adminStoreRegi( ){
        return "/admin/store/registration";
    }


    @RequestMapping(value = "/store/modify" , method = RequestMethod.GET)
    public String adminStoreModify( ){
        return "/admin/store/modify";
    }
}
