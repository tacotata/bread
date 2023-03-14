package com.example.helloproject.controller;

import com.example.helloproject.service.admin.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @RequestMapping(value = "/notice" , method = RequestMethod.GET)
    public String notice(Model model ){
        model.addAttribute("notice", newsService.findNoticeAllDesc());
        return "/news/notice";
    }

    @GetMapping(value = "/notice-detail/{id}")
    public String noticeDetail( @PathVariable Long id , Model model){
        model.addAttribute("notice", newsService.findById(id) );
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
