package com.example.helloproject.data.repository.user;

import com.example.helloproject.data.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
    Optional<Users> findByNameAndMobile(String name, String mobile);
    Optional<Users> findByEmailAndName(String email, String name);

}
