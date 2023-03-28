package com.example.helloproject.data.repository.news;

import com.example.helloproject.data.entity.news.News;
import com.example.helloproject.data.entity.news.NewsType;
import com.example.helloproject.data.entity.news.QNews;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class NewsRepositoryCustomImpl  implements NewsRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public NewsRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<News> searchNewsAll(NewsType newsType, String search, Pageable pageable) {
        QNews news = QNews.news;
        List<News> results = queryFactory
                .selectFrom(news)
                .where(news.type.eq(newsType), eqSubject(search))
                .orderBy(news.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(news.count())
                .from(news)
                .where(news.type.eq(newsType), eqSubject(search))
                .orderBy(news.id.desc());
        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqSubject(String search) {
        if (StringUtils.isEmpty(search)) {
            return null;
        }
        return QNews.news.subject.contains(search);
    }
}
