package com.example.helloproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping(value = "/store-pick" , method = RequestMethod.GET)
    public String storePick( ){
        return "/order/store-pick";
    }

    @RequestMapping(value = "/date-pick" , method = RequestMethod.GET)
    public String datePick( ){
        return "/order/date-pick";
    }

    @RequestMapping(value = "/menu-pick" , method = RequestMethod.GET)
    public String menuPick( ){
        return "/order/menu-pick";
    }

    @RequestMapping(value = "/menu-pick-detail" , method = RequestMethod.GET)
    public String menuPickDetail( ){
        return "/order/menu-pick-detail";
    }

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String orderList( ){
        return "/order/list";
    }


}
