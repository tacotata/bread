package com.example.helloproject.controller;

import com.example.helloproject.data.dto.admin.news.NewsSaveRequestDto;
import com.example.helloproject.data.dto.admin.news.NewsUpdateRequestDto;
import com.example.helloproject.service.UploadService;
import com.example.helloproject.service.admin.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/api/v1/news")
    @ResponseBody
    public Long save(@RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "key") NewsSaveRequestDto requestDto) throws IOException {
        log.info("===============NEWS SAVE START ====================");
        Long id = newsService.save(requestDto);
        log.info("INSERT NEWS TABLE ID : {} ", id);
        String type = String.valueOf(requestDto.getType());
        String result = "FAIL";
        int fileCnt = files.size();
        try {
            if (id != null) {
                if (fileCnt > 0) {
                    for (int i = 0; i <= fileCnt - 1; i++) {
                        log.info("===================================");
                        log.info("OriginalFilename : {} ", files.get(i).getOriginalFilename());
                        log.info("Size : {} ", files.get(i).getSize());
                        log.info("ContentType : {} ", files.get(i).getContentType());
                        log.info("===================================");
                    }
                    // news_file table insert
                    result = uploadService.saveFile(files, id, type);
                    log.info("NEWS_FILE TABLE INSERT RESULT {}", result);
                    if (result.equals("SUCCESS")) {
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

    @PutMapping("/api/v1/news/{id}")
    public Long update(@PathVariable Long id, @RequestBody NewsUpdateRequestDto requestDto) {
        return newsService.update(id, requestDto);
    }

    @RequestMapping(value = "/news/registration", method = RequestMethod.GET)
    public String adminNewsRegi() {
        return "/admin/news/registration";
    }

    @RequestMapping(value = "/news/modify", method = RequestMethod.GET)
    public String adminNoticeModify() {
        return "/admin/news/modify";
    }

    @RequestMapping(value = "/menu/registration", method = RequestMethod.GET)
    public String adminMenuRegi() {
        return "/admin/menu/registration";
    }


    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String adminMenuModify() {
        return "/admin/menu/modify";
    }


    @RequestMapping(value = "/store/registration", method = RequestMethod.GET)
    public String adminStoreRegi() {
        return "/admin/store/registration";
    }


    @RequestMapping(value = "/store/modify", method = RequestMethod.GET)
    public String adminStoreModify() {
        return "/admin/store/modify";
    }
}
