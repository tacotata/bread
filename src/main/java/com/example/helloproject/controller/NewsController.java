package com.example.helloproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/news")
public class NewsController {


    @RequestMapping(value = "/notice" , method = RequestMethod.GET)
    public String notice( ){
        return "/news/notice";
    }

    @RequestMapping(value = "/notice-detail" , method = RequestMethod.GET)
    public String noticeDetail( ){
        return "/news/notice-detail";
    }

    @RequestMapping(value = "/events" , method = RequestMethod.GET)
    public String events( ){
        return "/news/events";
    }

    @RequestMapping(value = "/event-detail" , method = RequestMethod.GET)
    public String eventDetail( ){
        return "/news/event-detail";
    }

    @RequestMapping(value = "/faq" , method = RequestMethod.GET)
    public String faq( ){
        return "/news/faq";
    }
}
