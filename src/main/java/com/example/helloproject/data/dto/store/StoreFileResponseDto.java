package com.example.helloproject.data.dto.store;

import com.example.helloproject.data.entity.store.StoreFile;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StoreFileResponseDto {
    private Long id;
    private String fileName;
    private String oriFileName;
    private String extension;
    private String filePath;
    private Long storeId;
    private LocalDateTime regDate;
    private LocalDateTime  updDate;

    @Builder
    public StoreFileResponseDto(StoreFile entity) {
        this.id =entity.getId();
        this.fileName = entity.getFileName();
        this.oriFileName = entity.getOriFileName();
        this.extension = entity.getExtension();
        this.filePath = entity.getFilePath();
        this.storeId = entity.getStoreId();
        this.regDate = entity.getRegDate();
        this.updDate = entity.getUpdDate();
    }


}
