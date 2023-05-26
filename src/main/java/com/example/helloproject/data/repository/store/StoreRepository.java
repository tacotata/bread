package com.example.helloproject.data.repository.store;

import com.example.helloproject.data.entity.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StoreRepository extends JpaRepository<Store, Long>, QuerydslPredicateExecutor<Store>, StoreRepositoryCustom {


}
