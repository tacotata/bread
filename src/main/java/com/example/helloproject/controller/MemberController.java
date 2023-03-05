package com.example.helloproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member")
public class MemberController {

    @RequestMapping(value = "/join" , method = RequestMethod.GET)
    public String join( ){
        return "/member/join";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String login( ){
        return "/member/login";
    }

    @RequestMapping(value = "/mypage" , method = RequestMethod.GET)
    public String mypage( ){
        return "/member/mypage";
    }

    @RequestMapping(value = "/cart" , method = RequestMethod.GET)
    public String cart( ){
        return "/member/cart";
    }


    @RequestMapping(value = "/modify-before" , method = RequestMethod.GET)
    public String modifyBefore( ){
        return "/member/modify-before";
    }


    @RequestMapping(value = "/modify" , method = RequestMethod.GET)
    public String modify( ){
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
