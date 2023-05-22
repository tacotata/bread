package com.example.helloproject.data.dto.users;

import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.entity.user.Withdraw;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawSaveRequestDto {

    private Long id;
    private Users users;
    private String reason;

    @Builder
    public WithdrawSaveRequestDto(Long id, Users users, String reason) {
        this.id = id;
        this.users = users;
        this.reason = reason;
    }

    public Withdraw toEntity(){
        return Withdraw.builder().users(users).reason(reason).build();
    }
}



