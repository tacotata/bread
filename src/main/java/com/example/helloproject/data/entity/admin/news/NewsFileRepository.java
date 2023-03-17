package com.example.helloproject.data.entity.admin.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsFileRepository  extends JpaRepository<NewsFile, Long> {

    @Query("SELECT n FROM NewsFile n WHERE  n.newsId = ?1")
    List<NewsFile> findByNoticeFileId(Long newsId);
}
