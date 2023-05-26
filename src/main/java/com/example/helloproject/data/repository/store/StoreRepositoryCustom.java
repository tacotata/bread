package com.example.helloproject.data.repository.store;

import com.example.helloproject.data.dto.item.MainItemDto;
import com.example.helloproject.data.dto.store.MainStoreDto;
import com.example.helloproject.data.entity.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StoreRepositoryCustom {

   Page<Store> searchStore(String search, Pageable pageable);
   Page<MainStoreDto> getStorePage(String search, Pageable pageable);

}
