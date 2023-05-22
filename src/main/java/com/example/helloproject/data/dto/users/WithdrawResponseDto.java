package com.example.helloproject.data.dto.users;

import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.entity.user.Withdraw;
import lombok.Getter;

@Getter
public class WithdrawResponseDto {
    private Long id;
    private Users users;
    private String reason;

    public WithdrawResponseDto(Withdraw entity){
        this.id = entity.getId();
        this.users = entity.getUsers();
        this.reason = entity.getReason();
    }
}
