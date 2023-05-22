package com.example.helloproject.data.entity.user;

import com.example.helloproject.data.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Withdraw extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    private String reason;

    @Builder
    public Withdraw(Long id, Users users, String reason) {
        this.id = id;
        this.users = users;
        this.reason = reason;
    }
}
