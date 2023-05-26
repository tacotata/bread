package com.example.helloproject.data.entity.news;

import com.example.helloproject.data.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class News extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private NewsType type;

    @NotNull
    private String subject;
    private String contents;
    private String regId;
    private String updId;
    private int fileCnt;
    //private LocalDateTime updDate;

    public void update(String subject, String contents, String updId, int fileCnt, NewsType type ) {
        this.subject = subject;
        this.contents = contents;
        this.updId = updId;
        this.fileCnt = fileCnt;
        this.type = type;
        //this.setUpdDate(LocalDateTime.now());
    }

    public void updateFileCnt(int fileCnt){
        this.fileCnt = fileCnt;
    }

    @Builder
    public News(NewsType type, String subject, String contents,  String regId, String updId, int fileCnt) {
        this.type = type;
        this.subject = subject;
        this.contents = contents;
        this.regId = regId;
        this.updId = updId;
        this.fileCnt = fileCnt;
    }

}
