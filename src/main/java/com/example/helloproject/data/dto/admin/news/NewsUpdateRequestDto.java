package com.example.helloproject.data.dto.admin.news;

import com.example.helloproject.data.entity.admin.news.NewsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsUpdateRequestDto {
    private NewsType type;
    private String subject;
    private String contents;
    private String updId;
    private int fileCnt;

    @Builder
    public NewsUpdateRequestDto(NewsType type,String subject, String contents, String updId, int fileCnt) {
        this.type = type;
        this.subject = subject;
        this.contents = contents;
        this.updId = updId;
        this.fileCnt = fileCnt;
    }


}
