package com.example.helloproject.data.dto.admin.news;

import com.example.helloproject.data.entity.admin.news.News;
import com.example.helloproject.data.entity.admin.news.NewsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsSaveRequestDto {

    private NewsType type;
    private String subject;
    private String contents;
    private String regId;
    private String updId;
    private int fileCnt;

    @Builder
    public NewsSaveRequestDto(NewsType type, String subject, String contents,  String regId, String updId, int fileCnt) {
        this.type = type;
        this.subject = subject;
        this.contents = contents;
        this.regId = regId;
        this.updId = updId;
        this.fileCnt = fileCnt;
    }

    public News toEntity(){
        return News.builder().type(type).subject(subject).contents(contents).regId(regId).updId(updId).fileCnt(fileCnt).build();
    }
}



