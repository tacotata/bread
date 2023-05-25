package com.example.helloproject.data.repository.cs;

import com.example.helloproject.data.entity.cs.ContactFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactFileRepository extends JpaRepository<ContactFile, Long> {

    List<ContactFile> findByContactId(Long contactId);
}
