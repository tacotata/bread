package com.example.helloproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    @RequestMapping(value="/reservation-list")
    public String ownerReservationList(){
        return "/owner/reservation-list";
    }
}
