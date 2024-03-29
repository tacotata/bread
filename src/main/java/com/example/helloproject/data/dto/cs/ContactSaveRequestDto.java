package com.example.helloproject.data.dto.cs;


import com.example.helloproject.data.entity.cs.Contact;
import com.example.helloproject.data.entity.cs.CsType;
import com.example.helloproject.data.entity.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactSaveRequestDto {
    private CsType csType;
    private String title;
    private String email;
    private String contents;

    @Builder
    public ContactSaveRequestDto(CsType csType, String title, String email, String contents) {
        this.csType = csType;
        this.title = title;
        this.email = email;
        this.contents = contents;
    }

    public Contact toEntity(){
        return Contact.builder().csType(csType).title(title).contents(contents).email(email).build();
    }
}



