package com.example.helloproject.data.entity.cs;

import com.example.helloproject.data.entity.BaseEntity;
import com.example.helloproject.data.entity.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CsType csType;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contents;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;



    @Builder
    public Contact(Long id, CsType csType, String title, String email, String contents, Users users) {
        this.id = id;
        this.csType = csType;
        this.title = title;
        this.email = email;
        this.contents = contents;
        this.users = users;
    }

    public String getCsTypeKey(){
        return this.csType.getKey();
    }

}
