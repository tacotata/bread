package com.example.helloproject.data.dto.admin.news;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NewsUpdateRequestDto {
    private String subject;
    private String contents;
    private String updId;
    private int fileCnt;

    @Builder
    public NewsUpdateRequestDto(String subject, String contents, String updId, int fileCnt) {
        this.subject = subject;
        this.contents = contents;
        this.updId = updId;
        this.fileCnt = fileCnt;
    }


}
