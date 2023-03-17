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

    @GetMapping(value = "/notice")
    public String notice(Model model){
        model.addAttribute("notice", newsService.findNoticeAllDesc());
        return "/news/notice";
    }

    @GetMapping(value = "/notice-detail/{id}")
    public String noticeDetail( @PathVariable Long id , Model model) {
        try {
            NewsResponseDto notice = newsService.findById(id);
            int fileCnt = notice.getFileCnt();
            log.info("fileCnt : {} ", fileCnt);
            if (fileCnt > 0) {
                model.addAttribute("file", uploadService.findByNoticeFileId(id));
            }
            model.addAttribute("notice", notice);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/news/notice-detail";
    }



    // 첨부 파일 다운로드
    @GetMapping("/attach/{newsId}/{fileName}")
    public void downloadAttach(@PathVariable Long newsId,@PathVariable String fileName, HttpServletResponse response )  {
        log.info("===============downloadAttach START ====================");
        try{
            List<NewsFileResponseDto> list = uploadService.findByNoticeFileId(newsId);
            String extension = "";
            String[] fileAttachArr = {".pdf", ".txt", ".xls", ".xlsx"};
            log.info(" list size {} ", list.size());

            for(int i=0; i < list.size(); i++ ) {
                extension = list.get(i).getExtension();
                for(int j =0; j < fileAttachArr.length; j++) {
                    if(fileAttachArr[j].equals(extension)){
                        log.info("NEWS FILE ID : {} ", String.valueOf(list.get(i).getId()));
                        log.info("NEWS ID : {} ",String.valueOf(newsId));
                        log.info("FILE NAME  : {} ", fileName);

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
