package com.example.helloproject.controller;


import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.item.MainItemDto;
import com.example.helloproject.data.dto.store.MainStoreDto;
import com.example.helloproject.data.dto.store.StoreResponseDto;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.service.StoreService;
import com.example.helloproject.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StoreController {

    private final StoreService storeService;
    private final UploadService uploadService;

    /*@GetMapping("/stores")
    public String stores(Model model, @LoginUser SessionUser user, @PageableDefault(size=9, sort="id", direction= Sort.Direction.DESC) Pageable pageable, String search){
        log.info("===============STORES START ====================");

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

        log.info("===============STORES END ====================");
        return "/store/stores";
    }*/

    @GetMapping("/stores")
    public String stores(Model model, @LoginUser SessionUser user, @PageableDefault(size=9, sort="id", direction= Sort.Direction.DESC) Pageable pageable, String search){
        log.info("===============STORES START ====================");

        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        Page<MainStoreDto> list = storeService.getStorePage(search, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1; //1 더해서 0+1 = 1부터 시작
        log.info("getTotalElements : {}", String.valueOf(list.getTotalElements()));
        log.info("총 페이지 개수 : {} ", list.getTotalPages()); // pageable은 -1 해야함
        log.info("검색 키워드 : {} ", search);
        log.info("현재 페이지 : {} ", nowPage );//1부터 시작 pageable은 0부터 시작

        model.addAttribute("store", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("maxPage", 5);// 페이징 수

        log.info("===============STORES END ====================");
        return "/store/stores";
    }

    @GetMapping("/stores/{id}")
    public String storeDetail(@PathVariable Long id, @RequestParam(value = "page", required=false) int page, Model model, @LoginUser SessionUser user){

        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }

        StoreResponseDto store = storeService.findById(id);
        model.addAttribute("file", uploadService.findByStoreId(id));
        model.addAttribute("store", store);
        model.addAttribute("page", page);// 0부터 시작

        return "/store/store-detail";
    }
}
