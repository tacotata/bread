package com.example.helloproject.controller;

import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.item.ItemFormDto;
import com.example.helloproject.data.dto.item.MainItemDto;
import com.example.helloproject.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemsController {

    private  final ItemsService itemsService;

//    @GetMapping("/items")
//    public String items(String search, Optional<Integer> page, Model model, @LoginUser SessionUser user ){
//        if(user !=null){
//            model.addAttribute("userName", user.getName());
//            model.addAttribute("role", user.getRole());
//        }
//
//        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 6);
//        Page<MainItemDto> items = itemsService.getItemPage(search,pageable);
//        model.addAttribute("items", items);
//
//        model.addAttribute("search", search);
//        model.addAttribute("maxPage", 5);
//
//        return "/items/item";
//    }


    @GetMapping("/items")
    public String items(String search, @PageableDefault(size=9, sort="id", direction= Sort.Direction.DESC) Pageable pageable, Model model, @LoginUser SessionUser user ){
        log.info("===============ITEMS START ====================");

        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }

        Page<MainItemDto> items = itemsService.getItemPage(search, pageable);

        // pageable은 0부터 시작
        int nowPage = items.getPageable().getPageNumber() + 1; //1 더해서 0+1 = 1부터 시작
        log.info("getTotalElements : {}", String.valueOf(items.getTotalElements()));
        log.info("총 페이지 개수 : {} ", items.getTotalPages()); // pageable은 -1 해야함
        log.info("검색 키워드 : {} ", search);
        log.info("현재 페이지 : {} ", nowPage );//1부터 시작 pageable은 0부터 시작

        model.addAttribute("items", items);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("maxPage", 5);// 페이징 수

        log.info("===============ITEMS END ====================");
        return "/items/item";
    }


    @GetMapping("/item/{itemsId}")
    public String getItemDetail( @PathVariable("itemsId")Long itemsId, @RequestParam(value = "page", required=false) int page,  Model model, @LoginUser SessionUser user ){
        log.info("===============ITEM-DETAIL START ====================");
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        ItemFormDto itemFormDto = itemsService.getItemDetail(itemsId);
        model.addAttribute("item", itemFormDto);
        model.addAttribute("page", page);// 0부터 시작

        log.info("===============ITEM-DETAIL END ====================");
        return "/items/item-detail";
    }
}
