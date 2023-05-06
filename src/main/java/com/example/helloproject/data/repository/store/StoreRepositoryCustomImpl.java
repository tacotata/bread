package com.example.helloproject.data.repository.store;

import com.example.helloproject.data.entity.menu.ItemsStatus;
import com.example.helloproject.data.entity.news.News;
import com.example.helloproject.data.entity.news.NewsType;
import com.example.helloproject.data.entity.news.QNews;
import com.example.helloproject.data.entity.store.QStore;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.repository.news.NewsRepositoryCustom;
import com.querydsl.core.types.Predicate;
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
public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public StoreRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Store> searchStore(String search, Pageable pageable) {
        QStore store = QStore.store;
        List<Store> results = queryFactory
                .selectFrom(store)
                .where(store.hide_yn.eq(Boolean.valueOf("false")))
                .where(eqAdrress(search))
                .orderBy(store.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(store.count())
                .from(store)
                .where(store.hide_yn.eq(Boolean.valueOf("false")))
                .where(eqAdrress(search))
                .orderBy(store.id.desc());
        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqAdrress(String search) {
        if (StringUtils.isEmpty(search)) {
            return null;
        }
        return QStore.store.address.contains(search);
    }

}
