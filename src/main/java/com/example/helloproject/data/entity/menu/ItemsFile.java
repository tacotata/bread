package com.example.helloproject.data.entity.menu;

import com.example.helloproject.data.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ItemsFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String oriFileName;

    private String filePath;

    private String extension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "items_id")
    private Items items;

    public void updateItemFile(String fileName, String oriFileName, String filePath, String extension){
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.extension = extension;
    }

    @Builder
    public ItemsFile(Long id, String fileName, String oriFileName, String filePath, String extension, Items items) {
        this.id = id;
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.extension = extension;
        this.items = items;
    }
}
