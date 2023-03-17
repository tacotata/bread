package com.example.helloproject.controller;

import com.example.helloproject.data.dto.admin.news.NewsFileResponseDto;
import com.example.helloproject.data.dto.admin.news.NewsResponseDto;
import com.example.helloproject.service.UploadService;
import com.example.helloproject.service.admin.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;
    private final UploadService uploadService;

    @GetMapping( "/notice")
    public String notice(Model model){
        model.addAttribute("notice", newsService.findNewsAllDesc());
        return "/news/notice";
    }

    @GetMapping("/notice-detail/{id}")
    public String noticeDetail( @PathVariable Long id , Model model) {
        try {
            NewsResponseDto notice = newsService.findById(id);
            int fileCnt = notice.getFileCnt();
            log.info("File CNT : {} ", fileCnt);
            if (fileCnt > 0) {
                model.addAttribute("file", uploadService.findByNewsId(id));
            }
            model.addAttribute("notice", notice);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/news/notice-detail";
    }



    // 첨부 파일 다운로드
    @GetMapping("/attach/{newsId}/{fileName}")
    public void downloadAttach(@PathVariable Long newsId, @PathVariable String fileName, HttpServletResponse response )  {
        log.info("===============downloadAttach START ====================");
        try{
            List<NewsFileResponseDto> list = uploadService.findByNewsId(newsId);
            String extension = "";
            String[] fileAttachArr = {".pdf", ".txt", ".xls", ".xlsx"};

            for(int i=0; i < list.size(); i++ ) {
                extension = list.get(i).getExtension();
                for(int j =0; j < fileAttachArr.length; j++) {
                    if(fileAttachArr[j].equals(extension)){
                        log.info("======================================");
                        log.info("NEWS FILE ID : {} ", String.valueOf(list.get(i).getId()));
                        log.info("NEWS ID : {} ",String.valueOf(newsId));
                        log.info("FILE NAME  : {} ", fileName);
                        log.info("======================================");

                        File f = new File(list.get(i).getFilePath(), fileName);
                        response.setContentType("application/download");
                        response.setContentLength((int)f.length());
                        response.setContentType("application/octet-stream");
                        response.setHeader("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");

                        FileInputStream fis = new FileInputStream(f);
                        OutputStream os = response.getOutputStream();
                        FileCopyUtils.copy(fis, os);
                        fis.close();
                        os.close();
                        break;
                    }
                }
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("===============downloadAttach END ====================");
    }

    @DeleteMapping("/api/v1/{id}")
    @ResponseBody
    public Long delete(@PathVariable Long id ) {
        log.info("===============NEWS DELETE START ====================");
        try {
            int fileCnt = newsService.delete(id);
            log.info("FILE CNT : {}" ,fileCnt);
            log.info("DELETE NEWS ID : {}" , id);
            if (fileCnt > 0) {
                List<Long> newsFilesIds = uploadService.delete(id);
                for(Long fileId : newsFilesIds) {
                    log.info("DELETE NEWS FILE ID : {}" , fileId);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("===============NEWS DELETE END ====================");
        return id;
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
