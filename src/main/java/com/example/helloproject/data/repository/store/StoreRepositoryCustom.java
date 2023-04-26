package com.example.helloproject.data.repository.store;

import com.example.helloproject.data.entity.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StoreRepositoryCustom {

   Page<Store> searchStore(String search, Pageable pageable);

}
