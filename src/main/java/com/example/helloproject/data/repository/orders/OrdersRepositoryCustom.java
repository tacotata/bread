package com.example.helloproject.data.repository.orders;

import com.example.helloproject.data.dto.orders.MainOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;


public interface OrdersRepositoryCustom {
   Page<MainOrderDto> getOrderPage(LocalDate startDate, LocalDate endDate, Long userId, Pageable pageable);
   Page<MainOrderDto> getStoreOrderPage(LocalDate startDate, LocalDate endDate, Long userId, Pageable pageable);

}
