package com.example.helloproject.controller;

import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.news.NewsResponseDto;
import com.example.helloproject.data.dto.news.NewsSaveRequestDto;
import com.example.helloproject.data.dto.news.NewsUpdateRequestDto;
import com.example.helloproject.data.dto.store.StoreResponseDto;
import com.example.helloproject.data.dto.store.StoreSaveRequestDto;
import com.example.helloproject.data.dto.store.StoreUpdateRequestDto;
import com.example.helloproject.service.StoreService;
import com.example.helloproject.service.UploadService;
import com.example.helloproject.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final NewsService newsService;
    private final UploadService uploadService;
    private final StoreService storeService;

    @PostMapping("/api/v1/news")
    @ResponseBody
    public Long save(@RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "key") NewsSaveRequestDto requestDto) throws IOException {
        log.info("===============NEWS SAVE START ====================");
        String type = String.valueOf(requestDto.getType());
        Long id =0L;
        Long fileId =0L;
        int fileCnt = files.size();
        try {
            if(type.isEmpty() || requestDto.getSubject().isEmpty() || (requestDto.getContents().isEmpty() && files.get(0).isEmpty()) ){
                log.info("NEWS 등록 is Empty");
                return Long.valueOf(0);
            }else{
                id = newsService.save(requestDto);
                log.info("INSERT NEWS TABLE ID : {} ", id);
            }
            if (id != 0) {
                if (!files.get(0).isEmpty()) {
                    for (int i = 0; i <= fileCnt - 1; i++) {
                        log.info("===================================");
                        log.info("OriginalFilename : {} ", files.get(i).getOriginalFilename());
                        log.info("Size : {} ", files.get(i).getSize());
                        log.info("ContentType : {} ", files.get(i).getContentType());
                        log.info("===================================");
                    }
                    fileId = uploadService.saveFiles(files, id, type);
                    if (fileId > 0) {
                        // news_file table insert
                        log.info("NEWS_FILE TABLE INSERT SUCCESS");
                        //news table  fileCnt update
                        Long updId = newsService.updateFileCnt(id, fileCnt);
                        log.info("INSERT ID {} , UPDATE ID {}", id, updId);
                        if (!id.equals(updId) ) {
                            //upload해야 할 id랑 upload된 id가 다름
                            return Long.valueOf(-2);
                        }
                    } else {
                        //news_file table insert 오류
                        return Long.valueOf(-1);
                    }
                }
            } else {
                // news table insert 오류
                return Long.valueOf(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("===============NEWS SAVE END ====================");
        return id;
    }

    @GetMapping("/news/registration")
    public String adminNewsRegi(Model model, @LoginUser SessionUser user) {
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/admin/news/registration";
    }

    @GetMapping("/news/modify/{id}")
    public String adminNewsModify(@PathVariable Long id, @RequestParam(value = "page", required=false) int page, Model model, @LoginUser SessionUser user ) {
        try {
            if(user !=null){
                model.addAttribute("userName", user.getName());
                model.addAttribute("role", user.getRole());
            }
                NewsResponseDto news = newsService.findById(id);
                int fileCnt = news.getFileCnt();
                log.info("FILE CNT : {} ", fileCnt);
                if (fileCnt > 0) {
                    model.addAttribute("file", uploadService.findByNewsId(id));
                }
                    model.addAttribute("news", news);
                    model.addAttribute("page", page);
        }catch (Exception e){
                e.printStackTrace();
        }
        return "/admin/news/modify";
    }

    @DeleteMapping("/api/v1/news/{newsId}/{fileId}")
    @ResponseBody
    public Long fileDelete(@PathVariable Long newsId, @PathVariable Long fileId, @RequestBody NewsUpdateRequestDto requestDto) {
        log.info("=============== fileDelete START ====================");
        uploadService.fileDelete(fileId);
        log.info("DELETE NEWS FILE ID : {} ", String.valueOf(fileId));
        //file cnt update
        Long id = newsService.updateFileCnt(newsId, requestDto.getFileCnt());
        log.info("UPDATE NEWS ID : {} ", String.valueOf(id));
        log.info("=============== fileDelete END ====================");
        return fileId;
    }


    @PutMapping("/api/v1/news/{id}")
    @ResponseBody
    public Long update(@PathVariable Long id , @RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "key") NewsUpdateRequestDto requestDto) {
        log.info("=============== news update START ====================");
        String type = String.valueOf(requestDto.getType());
        Long fileId = 0L;
        try{
            if (!files.get(0).isEmpty()) {
                log.info("FILE SIZE: {}", files.size());
                // news_file table insert
                fileId = uploadService.saveFiles(files, id, type);
                if (fileId >0) {
                    log.info("NEWS_FILE TABLE INSERT SUCCESS");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("=============== news update end ====================");
        return newsService.update(id, requestDto);
    }


    @RequestMapping(value = "/menu/registration", method = RequestMethod.GET)
    public String adminMenuRegi(Model model, @LoginUser SessionUser user) {
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/admin/menu/registration";
    }


    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String adminMenuModify(Model model, @LoginUser SessionUser user) {
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/admin/menu/modify";
    }


    @GetMapping("/store/registration")
    public String adminStoreRegi(Model model, @LoginUser SessionUser user) {
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/admin/store/registration";
    }

    @PostMapping("/api/v1/store")
    @ResponseBody
    public Long StoreSave(@RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "key") StoreSaveRequestDto requestDto) throws IOException {
        log.info("===============STORE SAVE START ====================");
        String type= "store";
        Long id = 0L;
       // Long fileId=0L;
        try {
            id = storeService.save(requestDto);
            log.info("INSERT STORE TABLE ID : {} ", id);
                if (id != 0 && !files.get(0).isEmpty()) {
                    log.info(" files.size() {}", files.size());
                    for (int i = 0; i < files.size(); i++) {
                        log.info("===================================");
                        log.info("OriginalFilename : {} ", files.get(i).getOriginalFilename());
                        log.info("Size : {} ", files.get(i).getSize());
                        log.info("ContentType : {} ", files.get(i).getContentType());
                        log.info("===================================");
                    }
                    Long fileId = uploadService.saveFiles(files, id, type);
                    if (fileId > 0) {
                        log.info("STORE_FILE TABLE INSERT ID : {}", fileId);
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("===============STORE SAVE END ====================");
        return id;
    }

    @GetMapping("/store/modify/{id}")
    public String adminStoreModify(@PathVariable Long id, @RequestParam(value = "page", required=false) int page, Model model, @LoginUser SessionUser user ) {
        try {
            if(user !=null){
                model.addAttribute("userName", user.getName());
                model.addAttribute("role", user.getRole());
            }
            StoreResponseDto store = storeService.findById(id);
            model.addAttribute("file", uploadService.findByStoreId(id));
            model.addAttribute("store", store);
            model.addAttribute("page", page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/store/modify";
    }

    //store , storeFile 수정
    @PutMapping("/api/v1/store/{id}/{fileId}")
    @ResponseBody
    public Long storeUpdate(@PathVariable Long id ,@PathVariable Long fileId, @RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "key") StoreUpdateRequestDto requestDto) {
        log.info("=============== STORE UPDATE START ====================");
        try{
            String type = "store";
            Long savedFileId;
            if (!files.get(0).isEmpty()) {
                // 기존 파일 삭제하고
                uploadService.storeFileDelete(fileId);
                //새로운 파일 저장
                savedFileId = uploadService.saveFiles(files, id, type);
                if (savedFileId > 0) {
                    log.info("STORE_FILE TABLE INSERT ID : {} ", savedFileId);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("=============== STORE UPDATE END ====================");
        return storeService.update(id, requestDto);
    }

    //store 숨김으로 변경
    @PutMapping("/store/api/v1/{id}")
    @ResponseBody
    public Long storeHide(@PathVariable Long id, @RequestBody StoreUpdateRequestDto requestDto) {
        log.info("=============== STORE HIDE START ====================");
        log.info(String.valueOf(requestDto.isHide_yn()));
        log.info("=============== STORE HIDE END ====================");
        return storeService.update(id, requestDto);
    }
}
