package com.example.helloproject.data.dto.item;

import com.example.helloproject.data.entity.menu.Items;
import com.example.helloproject.data.entity.menu.ItemsFile;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemFileSaveDto {
    private Long id;
    private String fileName;
    private String oriFileName;
    private String filePath;
    private String extension;
    private Items items;

    @Builder
    public ItemFileSaveDto(Long id, String fileName, String oriFileName, String filePath, String extension, Items items) {
        this.id = id;
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.extension = extension;
        this.items = items;
    }

    public ItemsFile toEntity() {
        return ItemsFile.builder()
                .id(id)
                .fileName(fileName)
                .oriFileName(oriFileName)
                .filePath(filePath)
                .extension(extension)
                .items(items)
                .build();
    }


}
