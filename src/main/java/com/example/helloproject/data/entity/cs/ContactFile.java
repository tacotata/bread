package com.example.helloproject.data.entity.cs;

import com.example.helloproject.data.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ContactFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private String fileName;

    private String oriFileName;

    private String filePath;

    private String extension;


    @Builder
    public ContactFile(Long id, Contact contact, String fileName, String oriFileName, String filePath, String extension) {
        this.id = id;
        this.contact = contact;
        this.fileName = fileName;
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.extension = extension;

    }
}
