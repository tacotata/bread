package com.example.helloproject.data.entity.admin.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE n.type = 0 ORDER BY n.id DESC")
    List<News> findNoticeAllDesc();
}
