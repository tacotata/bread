package com.example.helloproject.data.repository.user;

import com.example.helloproject.data.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

}
