package com.example.helloproject.controller;


import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.cart.*;
import com.example.helloproject.data.dto.item.ItemFormDto;
import com.example.helloproject.data.dto.item.MainItemDto;
import com.example.helloproject.data.dto.orders.MainOrderDto;
import com.example.helloproject.data.dto.store.StoreResponseDto;
import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.menu.Items;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.service.CartService;
import com.example.helloproject.service.ItemsService;
import com.example.helloproject.service.OrderService;
import com.example.helloproject.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    private final OrderService orderService;

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

        Long id = cartService.findByUserId(user.getId());

        if(id > 0){
            List<Long> cartItemList = cartService.deleteCartItems(id);
            if(!cartItemList.isEmpty()) {
                for (Long cartItem : cartItemList) {
                    log.info("DELETE CART ITEM ID : {}", cartItem);
                }
            }
            Long deletedCartId  = cartService.deleteCart(id);
            log.info("DELETE CART ID : {}" , deletedCartId);
        }

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
        Long cartItemId = 0L;
        try{
            CartResponseDto cartResponseDto = cartService.findById(cartId);
            if(cartResponseDto != null){
                log.info("cartId: {} , itemsId :{}", cartId, itemsId);
                Long id = cartService.getCartItem(cartId, itemsId);
                if(id >0){
                    log.info("cartItem.get().getId() {}",String.valueOf(id));
                    CartItemUpdateRequestDto cartItemUpdateRequestDto = CartItemUpdateRequestDto.builder()
                            .cart(Cart.builder().id(cartId).build())
                            .item(Items.builder().id(itemsId).build())
                            .count(requestDto.getCount())
                            .build();
                    cartItemId = cartService.updateCartItem(id, cartItemUpdateRequestDto );

                }else{

                    CartItemSaveRequestDto cartItemSaveRequestDto = CartItemSaveRequestDto.builder()
                            .cart(Cart.builder().id(cartId).build())
                            .item(Items.builder().id(itemsId).build())
                            .count(requestDto.getCount())
                            .build();

                    cartItemId = cartService.saveCartItem(cartItemSaveRequestDto);

                }
            }else{
                model.addAttribute("errorMsg", "잘못된 경로입니다. 처음부터 다시 시도해주세요.");
            }

        }catch (Exception e){
            log.info(e.getMessage());
        }
        return cartItemId;
    }

    @GetMapping(value = {"/cart/{cartId}", "/cart"})
    public String cart(@PathVariable(required = false) Long cartId, Model model, @LoginUser SessionUser user){

       try{
           if(user !=null){
               model.addAttribute("userName", user.getName());
               model.addAttribute("role", user.getRole());
           }
           List<CartItemDetailDto> cartItemDetailList = cartService.getCartItemList(user.getEmail());

           log.info("장바구니 리스트 개수 : {}",cartItemDetailList.size());
           if(!cartItemDetailList.isEmpty() || cartItemDetailList != null || cartItemDetailList.size() != 0 ){
                   model.addAttribute("cartItems", cartItemDetailList);
                   CartDetailDto cartDetailDto = cartService.getCartList( cartItemDetailList.get(0).getCartId());
                   log.info("CART ID {}" ,cartDetailDto.getCartId());
                   model.addAttribute("cart",cartDetailDto);
           }
       }catch(Exception e){
           log.info(e.getMessage());
       }
        return "/order/cart";
    }

    @DeleteMapping("/api/v1/cart/{cartId}")
    @ResponseBody
    public void delete(@PathVariable Long cartId ) {
        try {
            List<Long> cartItemList = cartService.deleteCartItems(cartId);
            for(Long cartItem : cartItemList) {
                log.info("DELETE CART ITEM ID : {}" , cartItem);
            }
            if(!cartItemList.isEmpty()){
                Long deletedCartId  = cartService.deleteCart(cartId);
                log.info("DELETE CART ID : {}" , deletedCartId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   //결제하기
    @PostMapping("/api/v1/order/{cartId}")
    public @ResponseBody ResponseEntity orderCartItem(@PathVariable Long cartId, @RequestBody CartOrderDto cartOrderDto, @LoginUser SessionUser user ){
        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();
        Long orderId = cartService.orderCartItem(cartOrderDtoList, user.getEmail(), cartId);
        log.info("주문 ID : {}", orderId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    //고객 주문 리스트
    @GetMapping(value ={"/list", "/list/{page}"})
    public String orderList(@PageableDefault(size=9, sort="regDate", direction= Sort.Direction.DESC) Pageable pageable, Model model, @LoginUser SessionUser user, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate ){
        log.info("===============ORDER LIST START ====================");

        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        try{
            Page<MainOrderDto> orders = orderService.getOrderPage(startDate, endDate, user.getId(), pageable);
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

        log.info("===============ORDER LIST END ====================");
        return "/order/list";
    }



    @DeleteMapping("/api/v1/cartItem/{cartItemId}")
    @ResponseBody
    public void deleteCartItem(@PathVariable Long cartItemId ) {
        log.info("deleteCartItem start");
        try {
            cartService.deleteCartItem(cartItemId);
            log.info("DELETE CART ITEM ID : {}" , cartItemId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @PutMapping("/api/v1/cartItemCnt/{cartItemId}")
    @ResponseBody
    public Long updateCartItemCnt(@PathVariable Long cartItemId,  @RequestParam(value = "count", required=false) String count) {
        log.info("=============== updateCartItemCnt UPDATE START ====================");
        log.info(count);
        int countInt = Integer.parseInt(count);
        log.info("=============== updateCartItemCnt UPDATE END ====================");
        return cartService.updateCartItemCnt(cartItemId, countInt );

    }
}
