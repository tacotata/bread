package com.example.helloproject.data.dto.news;

import com.example.helloproject.data.entity.news.NewsType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NewsDetailDto {

    private Long id;
    private NewsType type;
    private LocalDateTime regDate;
    private LocalDateTime updDate;
    private String fileName;

    public NewsDetailDto(Long id, NewsType type, LocalDateTime regDate, LocalDateTime updDate, String fileName) {
        this.id = id;
        this.type = type;
        this.regDate = regDate;
        this.updDate = updDate;
        this.fileName = fileName;
    }
}
