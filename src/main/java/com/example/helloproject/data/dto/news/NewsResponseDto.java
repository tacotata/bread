package com.example.helloproject.data.dto.news;

import com.example.helloproject.data.entity.news.News;
import com.example.helloproject.data.entity.news.NewsType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NewsResponseDto {
    private Long id;
    private NewsType type;
    private String subject;
    private String contents;
    private LocalDateTime regDate;
    private LocalDateTime  updDate;
    private int fileCnt;

    public NewsResponseDto(News entity){
        this.id = entity.getId();
        this.type = entity.getType();
        this.subject = entity.getSubject();
        this.contents = entity.getContents();
        this.regDate = entity.getRegDate();
        this.updDate = entity.getUpdDate();
        this.fileCnt = entity.getFileCnt();
        this.type = entity.getType();
    }
}
