package com.example.helloproject.data.dto.item;

import com.example.helloproject.data.entity.menu.ItemsFile;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemsFileResponseDto {
    private Long id;
    private String fileName;
    private String oriFileName;
    private String filePath;
    private String extension;
//    private String regId;
//    private String updId;
    private LocalDateTime regDate;
    private LocalDateTime  updDate;

    @Builder
    public ItemsFileResponseDto(ItemsFile entity) {
        this.id =entity.getId();
        this.fileName = entity.getFileName();
        this.oriFileName = entity.getOriFileName();
        this.filePath = entity.getFilePath();
        this.extension = entity.getExtension();
        this.regDate = entity.getRegDate();
        this.updDate = entity.getUpdDate();
    }
}
