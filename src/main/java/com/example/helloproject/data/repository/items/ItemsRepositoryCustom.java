package com.example.helloproject.data.repository.items;

import com.example.helloproject.data.dto.item.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemsRepositoryCustom {
   Page<MainItemDto> getItemPage(String search, Pageable pageable);
}
