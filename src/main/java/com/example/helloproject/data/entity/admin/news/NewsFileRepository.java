package com.example.helloproject.data.entity.admin.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsFileRepository  extends JpaRepository<NewsFile, Long> {

    @Query("SELECT n FROM NewsFile n WHERE  n.newsId = ?1")
    List<NewsFile> findByNewsId(Long newsId);

    @Query("SELECT id FROM NewsFile n WHERE n.newsId = ?1")
    List<Long> findByNewsFileIds(Long newsId);

    @Modifying(clearAutomatically = true)
    @Query("delete from NewsFile n where n.id in :ids")
    void deleteAllByNewsFileIds(@Param("ids") List<Long> ids);

}
