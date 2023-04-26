package com.example.helloproject.data.entity.store;

import com.example.helloproject.data.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StoreFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String oriFileName;
    private String extension;
    private String filePath;
    private Long storeId;


    @Builder
    public StoreFile(Long id, String fileName, String oriFileName, String extension, String filePath, Long storeId) {
        this.id = id;
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.extension = extension;
        this.filePath = filePath;
        this.storeId = storeId;
    }
}
