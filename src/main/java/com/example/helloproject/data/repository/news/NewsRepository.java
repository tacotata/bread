package com.example.helloproject.data.repository.news;

import com.example.helloproject.data.dto.news.NewsDetailDto;
import com.example.helloproject.data.entity.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> , QuerydslPredicateExecutor<News>, NewsRepositoryCustom{

    @Query("SELECT n FROM News n ORDER BY n.id DESC")
    List<News> findNewsAllDesc();

    Page<News> findBySubjectContaining(String search, Pageable pageable);

    @Query("select new com.example.helloproject.data.dto.news.NewsDetailDto( n.id, n.type, n.regDate, nf.updDate, nf.fileName) " +
            "from News n " +
            "join NewsFile nf " +
            "on n.id = nf.newsId " +
            "where type not in (2) " +
            "order by n.regDate desc"
    )
    List<NewsDetailDto> findNewsAndNewsFile();
}
