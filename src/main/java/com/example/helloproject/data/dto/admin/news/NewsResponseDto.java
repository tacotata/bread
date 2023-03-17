package com.example.helloproject.data.dto.admin.news;

import com.example.helloproject.data.entity.admin.news.News;
import com.example.helloproject.data.entity.admin.news.NewsType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NewsResponseDto {

    private Long id;
    private NewsType type;
    private String subject;
    private String contents;
    private String regId;
    private String updId;
    private LocalDateTime regDate;
    private LocalDateTime  updDate;
    private int fileCnt;

    public NewsResponseDto(News entity){
        this.id = entity.getId();
        this.type = entity.getType();
        this.subject = entity.getSubject();
        this.contents = entity.getContents();
        this.regId = entity.getRegId();
        this.updId = entity.getUpdId();
        this.regDate = entity.getRegDate();
        this.updDate = entity.getUpdDate();
        this.fileCnt = entity.getFileCnt();
    }
}
