package com.example.helloproject.data.repository.user;

import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.entity.user.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
    Withdraw findByUsers(Users users);
}
