package com.example.helloproject.data.repository.items;

import com.example.helloproject.data.entity.menu.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemsRepository extends JpaRepository<Items, Long> , QuerydslPredicateExecutor<Items>, ItemsRepositoryCustom {
}
