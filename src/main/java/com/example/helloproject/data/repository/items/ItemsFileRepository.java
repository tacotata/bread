package com.example.helloproject.data.repository.items;

import com.example.helloproject.data.entity.menu.ItemsFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsFileRepository extends JpaRepository<ItemsFile, Long> {

    List<ItemsFile> findByItemsIdOrderByIdAsc(Long itemId);
}
