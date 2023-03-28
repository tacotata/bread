package com.example.helloproject.data.dto.news;

import com.example.helloproject.data.entity.news.NewsFile;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NewsFileResponseDto {
    private Long id;
    private Long newsId;
    private String fileName;
    private String oriFileName;
    private String filePath;
    private String extension;
    private String regId;
    private String updId;
    private LocalDateTime regDate;
    private LocalDateTime  updDate;

    @Builder
    public NewsFileResponseDto(NewsFile entity) {
        this.id =entity.getId();
        this.newsId = entity.getNewsId();
        this.fileName = entity.getFileName();
        this.oriFileName = entity.getOriFileName();
        this.filePath = entity.getFilePath();
        this.extension = entity.getExtension();
        this.regId = entity.getRegId();
        this.updId = entity.getUpdId();
        this.regDate = entity.getRegDate();
        this.updDate = entity.getUpdDate();
    }


}
