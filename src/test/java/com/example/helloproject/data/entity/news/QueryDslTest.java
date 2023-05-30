package com.example.helloproject.data.entity.news;

import com.example.helloproject.data.repository.news.NewsRepository;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class QueryDslTest {

    @Autowired
    NewsRepository newsRepository;

//    @Test
//    public void queryDSLTEST1(){
//        Predicate predicate = QNews.news.subject.containsIgnoreCase("ㄷㄷ")
//                .and(QNews.news.type.eq(NewsType.NOTICE));
//        Optional<News> foundNews = newsRepository.findOne(predicate);
//
//        if(foundNews.isPresent()){
//            News news = foundNews.get();
//            System.out.println(news.getId());
//            System.out.println(news.getType());
//            System.out.println(news.getSubject());
//            System.out.println(news.getContents());
//            System.out.println(news.getRegDate());
//            System.out.println(news.getUpdDate());
//        }
//    }

}