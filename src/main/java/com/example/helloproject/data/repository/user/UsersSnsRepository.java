package com.example.helloproject.data.repository.user;


import com.example.helloproject.data.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersSnsRepository extends JpaRepository<Users, Long> {

   Optional<Users> findByEmail(String email);
}
