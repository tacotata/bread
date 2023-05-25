package com.example.helloproject.data.dto.cs;

import com.example.helloproject.data.entity.cs.Contact;
import com.example.helloproject.data.entity.cs.ContactFile;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ContactFileSaveDto {
    private Long id;
    private Contact contact;
    private String fileName;
    private String oriFileName;
    private String filePath;
    private String extension;


    @Builder
    public ContactFileSaveDto(Long id, Contact contact, String fileName, String oriFileName, String filePath, String extension) {
        this.id = id;
        this.contact = contact;
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.extension = extension;

    }

    public ContactFile toEntity() {
        return ContactFile.builder()
                .id(id)
                .contact(contact)
                .fileName(fileName)
                .oriFileName(oriFileName)
                .filePath(filePath)
                .extension(extension)
                .build();
    }


}
