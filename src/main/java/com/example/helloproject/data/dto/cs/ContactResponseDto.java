package com.example.helloproject.data.dto.cs;

import com.example.helloproject.data.entity.cs.Contact;
import com.example.helloproject.data.entity.cs.CsType;
import com.example.helloproject.data.entity.user.Users;
import lombok.Getter;


@Getter
public class ContactResponseDto {
    private Long id;
    private CsType csType;
    private String title;
    private String email;
    private String contents;
    private Users users;

    public ContactResponseDto(Contact entity){
        this.id = entity.getId();
        this.csType = entity.getCsType();
        this.title = entity.getTitle();
        this.email = entity.getEmail();
        this.contents = entity.getContents();
        this.users = entity.getUsers();
    }
}
