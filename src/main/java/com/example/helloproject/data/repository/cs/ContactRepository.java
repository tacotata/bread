package com.example.helloproject.data.repository.cs;

import com.example.helloproject.data.entity.cs.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {


}
