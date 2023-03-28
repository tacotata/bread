package com.example.helloproject.data.dto.news;

import com.example.helloproject.data.entity.news.NewsFile;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NewsFileSaveDto {

    private Long newsId;
    private String fileName;
    private String oriFileName;
    private String filePath;
    private String extension;
    private String regId;
    private String updId;


    @Builder
    public NewsFileSaveDto(Long newsId, String fileName, String oriFileName, String filePath, String extension, String regId, String updId) {
        this.newsId = newsId;
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.extension = extension;
        this.regId = regId;
        this.updId = updId;
    }

    public NewsFile toEntity() {
        return NewsFile.builder()
                .newsId(newsId)
                .fileName(fileName)
                .oriFileName(oriFileName)
                .filePath(filePath)
                .extension(extension)
                .regId(regId)
                .updId(updId)
                .build();
    }
}
