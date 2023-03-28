package com.example.helloproject.data.repository.news;

import com.example.helloproject.data.entity.news.News;
import com.example.helloproject.data.entity.news.NewsType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NewsRepositoryCustom {
   Page<News> searchNewsAll(NewsType newsType, String search, Pageable pageable);
}
