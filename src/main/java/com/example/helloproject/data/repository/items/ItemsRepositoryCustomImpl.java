package com.example.helloproject.data.repository.items;

import com.example.helloproject.data.dto.item.MainItemDto;
import com.example.helloproject.data.dto.item.QMainItemDto;
import com.example.helloproject.data.entity.menu.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class ItemsRepositoryCustomImpl implements ItemsRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ItemsRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression itemNameLike(String search){
        return StringUtils.isEmpty(search) ? null : QItems.items.name.like("%" + search + "%");
    }

    @Override
    public Page<MainItemDto> getItemPage(String search, Pageable pageable) {
        QItems item = QItems.items;
        QItemsFile itemsFile = QItemsFile.itemsFile;
        QueryResults<MainItemDto> results = queryFactory
                .select(
                        new QMainItemDto(
                                item.id,
                                item.name,
                                item.info,
                                item.price,
                                itemsFile.regDate,
                                itemsFile.updDate,
                                itemsFile.fileName,
                                item.itemsType)
                )
                .from(itemsFile)
                .join(itemsFile.items, item)
                .where(item.itemsStatus.eq(ItemsStatus.valueOf("SELL")))
                .where(itemNameLike(search))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();


        List<MainItemDto> content = results.getResults();
        log.info("content {}", content);
        long total = results.getTotal();
        log.info("total {}", total);
        return new PageImpl<>(content, pageable, total);
    }
}
