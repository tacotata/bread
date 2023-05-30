package com.example.helloproject.data.repository.orders;


import com.example.helloproject.data.dto.orders.MainOrderDto;
import com.example.helloproject.data.dto.orders.QMainOrderDto;
import com.example.helloproject.data.entity.orders.QOrderItem;
import com.example.helloproject.data.entity.orders.QOrders;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Slf4j
public class OrdersRepositoryCustomImpl implements OrdersRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public OrdersRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression regDateBetween(LocalDate startDate, LocalDate endDate){

        LocalDate todayLocalDate = LocalDate.now();
        LocalDate oneMonthBefore = todayLocalDate.minusMonths(1);

        if(startDate == null && endDate == null){
            startDate = oneMonthBefore;
            endDate = todayLocalDate;
        }else if(startDate == null && endDate !=null ){
            startDate = endDate.minusMonths(1);
        }else if(startDate != null && endDate == null ){
            endDate = startDate.plusMonths(1);
        }

        BooleanExpression isGoeStartDate = QOrders.orders.regDate.goe(LocalDateTime.of(startDate, LocalTime.MIN));
        BooleanExpression isLoeEndDate = QOrders.orders.regDate.loe(LocalDateTime.of(endDate, LocalTime.MAX).withNano(0));

        return Expressions.allOf(isGoeStartDate, isLoeEndDate);

    }

    private BooleanExpression reserveDateBetween(LocalDate startDate, LocalDate endDate){

        LocalDate todayLocalDate = LocalDate.now();
        LocalDate oneMonthBefore = todayLocalDate.minusMonths(1);

        if(startDate == null && endDate == null){
            startDate = oneMonthBefore;
            endDate = todayLocalDate;
        }else if(startDate == null && endDate !=null ){
            startDate = endDate.minusMonths(1);
        }else if(startDate != null && endDate == null ){
            endDate = startDate.plusMonths(1);
        }

        BooleanExpression isGoeStartDate = QOrders.orders.reservedDate.goe(String.valueOf(LocalDateTime.of(startDate, LocalTime.MIN)));
        BooleanExpression isLoeEndDate = QOrders.orders.reservedDate.loe(String.valueOf(LocalDateTime.of(endDate, LocalTime.MAX).withNano(0)));

        return Expressions.allOf(isGoeStartDate, isLoeEndDate);

    }

    @Override
    public Page<MainOrderDto> getOrderPage(LocalDate startDate, LocalDate endDate, Long userId, Pageable pageable) {
        QOrders orders = QOrders.orders;
        QOrderItem orderItem = QOrderItem.orderItem;
        QueryResults<MainOrderDto> results = queryFactory
                .select(
                        new QMainOrderDto(
                                orders.id,
                                orders.regDate,
                                orders.reservedDate,
                                orders.reservedTime,
                                orders.store,
                                orders.users,
                                orders.orderStatus,
                                orderItem.count,
                                orderItem.orderPrice,
                                orderItem.item
                        )
                )
                .from(orderItem)
                .join(orderItem.orders, orders)
                .where(orders.users.id.eq(userId))
                .where(regDateBetween(startDate, endDate))
                .orderBy(orders.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MainOrderDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainOrderDto> getStoreOrderPage(LocalDate startDate, LocalDate endDate, Long storeId, Pageable pageable) {
        QOrders orders = QOrders.orders;
        QOrderItem orderItem = QOrderItem.orderItem;
        QueryResults<MainOrderDto> results = queryFactory
                .select(
                        new QMainOrderDto(
                                orders.id,
                                orders.regDate,
                                orders.reservedDate,
                                orders.reservedTime,
                                orders.store,
                                orders.users,
                                orders.orderStatus,
                                orderItem.count,
                                orderItem.orderPrice,
                                orderItem.item
                        )
                )
                .from(orderItem)
                .join(orderItem.orders, orders)
                .where(orders.store.id.eq(storeId))
                .where(reserveDateBetween(startDate, endDate))
                .orderBy(orders.reservedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MainOrderDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
