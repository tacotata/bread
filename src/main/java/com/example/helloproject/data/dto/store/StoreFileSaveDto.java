package com.example.helloproject.data.dto.store;

import com.example.helloproject.data.entity.store.StoreFile;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StoreFileSaveDto {

    private String fileName;
    private String oriFileName;
    private String extension;
    private String filePath;
    private Long storeId;

    @Builder
    public StoreFileSaveDto( String fileName, String oriFileName, String extension, String filePath, Long storeId) {
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.extension = extension;
        this.filePath = filePath;
        this.storeId = storeId;
    }

    public StoreFile toEntity() {
        return StoreFile.builder()
                .fileName(fileName)
                .oriFileName(oriFileName)
                .filePath(filePath)
                .extension(extension)
                .filePath(filePath)
                .storeId(storeId)
                .build();
    }
}
