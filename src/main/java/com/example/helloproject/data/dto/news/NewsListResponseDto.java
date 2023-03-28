package com.example.helloproject.data.dto.news;

import com.example.helloproject.data.entity.news.News;
import com.example.helloproject.data.entity.news.NewsType;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class NewsListResponseDto {
    private Long id;
    private NewsType type;
    private String subject;
    private String contents;
    private LocalDateTime regDate;

    public NewsListResponseDto(News entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.subject = entity.getSubject();
        this.contents = entity.getContents();
        this.regDate = entity.getRegDate();
    }
}
