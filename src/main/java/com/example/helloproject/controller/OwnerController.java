package com.example.helloproject.controller;

import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.orders.MainOrderDto;
import com.example.helloproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {

    private final OrderService orderService;

    @GetMapping(value ={"/order-list", "/order-list/{page}"})
    public String orderList(@PageableDefault(size=9, sort="reservedDate", direction= Sort.Direction.DESC) Pageable pageable, Model model, @LoginUser SessionUser user, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate ){
        log.info("===============OWNER RESERVATION  LIST START ====================");

        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        try{
            Page<MainOrderDto> orders = orderService.getStoreOrderPage(startDate, endDate, user.getStoreId(), pageable);
            // pageable은 0부터 시작
            int nowPage = orders.getPageable().getPageNumber() + 1; //1 더해서 0+1 = 1부터 시작
            log.info("getTotalElements : {}", String.valueOf(orders.getTotalElements()));
            log.info("총 페이지 개수 : {} ", orders.getTotalPages()); // pageable은 -1 해야함
            log.info("START DATE : {} ", startDate);
            log.info("END DATE : {} ", endDate);
            log.info("현재 페이지 : {} ", nowPage );//1부터 시작 pageable은 0부터 시작

            model.addAttribute("orders", orders);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("maxPage", 5);// 페이징 수
        }catch (Exception e){
            log.info(e.getMessage());
        }

        log.info("===============OWNER RESERVATION END ====================");
        return "/owner/order-list";
    }
}
