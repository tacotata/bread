package com.example.helloproject.data.dto.store;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MainStoreDto {

    private Long id;
    private String name;
    private String address;
    private LocalDateTime updDate;
    private String fileName;


    @QueryProjection

    public MainStoreDto(Long id, String name, String address, LocalDateTime updDate, String fileName) {
        this.id = id;
        this.name = name;
        this.address = address;

        this.updDate = updDate;
        this.fileName = fileName;
    }
}
