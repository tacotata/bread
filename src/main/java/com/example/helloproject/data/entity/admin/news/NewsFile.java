package com.example.helloproject.data.entity.admin.news;

import com.example.helloproject.data.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class NewsFile  extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long newsId;

    private String fileName;

    private String oriFileName;

    private String filePath;

    private String extension;

    private String regId;

    private String updId;

    @Builder
    public NewsFile(Long id, Long newsId, String fileName, String oriFileName, String extension, String filePath, String regId, String updId) {
        this.id = id;
        this.newsId = newsId;
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.extension = extension;
        this.regId = regId;
        this.updId = updId;
    }
}
