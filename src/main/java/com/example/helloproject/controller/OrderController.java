package com.example.helloproject.controller;


import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.cart.CartItemSaveRequestDto;
import com.example.helloproject.data.dto.cart.CartSaveRequestDto;
import com.example.helloproject.data.dto.item.ItemFormDto;
import com.example.helloproject.data.dto.item.MainItemDto;
import com.example.helloproject.data.dto.store.StoreResponseDto;
import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.menu.Items;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.service.CartService;
import com.example.helloproject.service.ItemsService;
import com.example.helloproject.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final StoreService storeService;
    private final CartService cartService;
    private final ItemsService itemsService;

    @GetMapping("/store-pick")
    public String storePick(Model model, @LoginUser SessionUser user, @PageableDefault(size=9, sort="id", direction= Sort.Direction.DESC) Pageable pageable, String search  ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }

        Page<Store> list = storeService.searchStore(search, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1; //1 더해서 0+1 = 1부터 시작
        log.info("getTotalElements : {}", String.valueOf(list.getTotalElements()));
        log.info("총 페이지 개수 : {} ", list.getTotalPages()); // pageable은 -1 해야함
        log.info("검색 키워드 : {} ", search);
        log.info("현재 페이지 : {} ", nowPage );//1부터 시작 pageable은 0부터 시작

        model.addAttribute("store", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("maxPage", 5);// 페이징 수


        return "/order/store-pick";
    }

    @GetMapping("/date-pick")
    public String datePick(Model model, @LoginUser SessionUser user, @RequestParam(value = "storeId", required=false) Long storeId){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }

        StoreResponseDto store = storeService.findById(storeId);

        int idx = store.getStartPickupTime().indexOf(":");
        LocalTime startPickupTime = LocalTime.of(Integer.parseInt(store.getStartPickupTime().substring(0,idx)), Integer.parseInt(store.getStartPickupTime().substring(idx+1)));
        LocalTime endPickupTime = LocalTime.of(Integer.parseInt(store.getEndPickupTime().substring(0,idx)), Integer.parseInt(store.getEndPickupTime().substring(idx+1)));

        log.info("startPickupTime {}",startPickupTime);
        log.info("endPickupTime {}",endPickupTime);
        List<LocalTime> pickupTimeList = new ArrayList<>();
        pickupTimeList.add(startPickupTime);

        for(int i =0; i <= 100; i++){
            if(startPickupTime.equals(endPickupTime)){
                break;
            }
            startPickupTime = startPickupTime.plusMinutes(30);
            pickupTimeList.add(startPickupTime);
        }
        model.addAttribute("userId", user.getId());
        model.addAttribute("storeId", storeId);
        model.addAttribute("store", store);
        model.addAttribute("pickupTimeList", pickupTimeList);

        return "/order/date-pick";
    }


    @PostMapping("/api/v1/datePick/{storeId}")
    @ResponseBody
    public Long saveDatePick(@PathVariable Long storeId, Model model, @LoginUser SessionUser user, @RequestBody CartSaveRequestDto requestDto){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        log.info(requestDto.getReservedDate());
        log.info(String.valueOf(storeId));

        log.info(String.valueOf(user.getId()));

        CartSaveRequestDto cartSaveRequestDto = CartSaveRequestDto.builder()
                .store(Store.builder().id(storeId).build())
                .users(Users.builder().id(user.getId()).build())
                .reservedDate(requestDto.getReservedDate())
                .reservedTime(requestDto.getReservedTime())
                .build();


        Long cartId = cartService.save(cartSaveRequestDto);

        return cartId;
    }

    @GetMapping("/menu-pick/{cartId}")
    public String menuPick(@PathVariable Long cartId, String search, @PageableDefault(size=9, sort="id", direction= Sort.Direction.DESC) Pageable pageable, Model model, @LoginUser SessionUser user  ){

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

        model.addAttribute("cartId", cartId);
        model.addAttribute("items", items);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("maxPage", 5);// 페이징 수

        return "/order/menu-pick";
    }

    @GetMapping("/menu-pick-detail/{itemsId}")
    public String menuPickDetail(@PathVariable Long itemsId, @RequestParam(value = "page", required=false) int page, @RequestParam(value = "cartId", required=false) Long cartId, Model model, @LoginUser SessionUser user  ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }

        ItemFormDto itemFormDto = itemsService.getItemDetail(itemsId);

        model.addAttribute("item", itemFormDto);
        model.addAttribute("page", page);// 0부터 시작
        model.addAttribute("cartId", cartId);

        return "/order/menu-pick-detail";
    }

    @PostMapping("/api/v1/cartItem/{itemsId}")
    @ResponseBody
    public Long saveCartItem(@PathVariable Long itemsId, @RequestParam(value = "page", required=false) int page, @RequestParam(value = "cartId", required=false)Long cartId, Model model, @LoginUser SessionUser user, @RequestBody CartItemSaveRequestDto requestDto){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }

        //장바구니에 userId랑 itemId 있으면 update로 해야하네

        CartItemSaveRequestDto cartItemSaveRequestDto = CartItemSaveRequestDto.builder()
                .cart(Cart.builder().id(cartId).build())
                .item(Items.builder().id(itemsId).build())
                .count(requestDto.getCount())
                .build();

        Long cartItemId = cartService.saveCartItem(cartItemSaveRequestDto);

        return cartItemId;
    }

    @GetMapping("/cart/{cartId}")
    public String cart(@PathVariable Long cartId, Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        //cartItem select해서 가져와야함


        return "/order/cart";
    }


    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String orderList(Model model, @LoginUser SessionUser user  ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/order/list";
    }


}
