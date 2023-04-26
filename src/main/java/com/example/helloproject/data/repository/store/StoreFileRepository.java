package com.example.helloproject.data.repository.store;

import com.example.helloproject.data.entity.store.StoreFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreFileRepository extends JpaRepository<StoreFile, Long> {

    @Query("SELECT n FROM StoreFile n WHERE  n.storeId = ?1")
     StoreFile findByStoreId(Long storeId);

    @Query("SELECT n FROM StoreFile n ORDER BY n.storeId DESC")
    List<StoreFile> findStoreFileAllDesc();
}
