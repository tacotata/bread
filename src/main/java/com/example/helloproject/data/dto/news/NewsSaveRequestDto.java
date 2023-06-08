package com.example.helloproject.data.dto.news;

import com.example.helloproject.data.entity.news.News;
import com.example.helloproject.data.entity.news.NewsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsSaveRequestDto {

    private NewsType type;
    private String subject;
    private String contents;
    private int fileCnt;

    @Builder
    public NewsSaveRequestDto(NewsType type, String subject, String contents, int fileCnt) {
        this.type = type;
        this.subject = subject;
        this.contents = contents;
        this.fileCnt = fileCnt;
    }

    public News toEntity(){
        return News.builder().type(type).subject(subject).contents(contents).fileCnt(fileCnt).build();
    }
}



