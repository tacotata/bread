package com.example.helloproject.utils;

import com.example.helloproject.data.dto.cart.CartResponseDto;

import com.example.helloproject.data.repository.cart.CartRepository;
import com.example.helloproject.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableAsync
public class Scheduler {
  private final CartRepository cartRepository;
  private final CartService cartService;

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() throws InterruptedException {
        log.info("===========SCHEDULER START=================");
        log.info("Fixed delay task - {}", System.currentTimeMillis() / 1000);
        LocalDateTime now = LocalDateTime.now();
        List<CartResponseDto> cart = cartRepository.findAll().stream().map(CartResponseDto::new).collect(Collectors.toList());
        try {
          if(!cart.isEmpty()) {
            for (int i = 0; i < cart.size(); i++) {
              log.info("REGDATE {}", String.valueOf(cart.get(i).getRegDate()));
              log.info("CART ID {}", String.valueOf(cart.get(i).getId()));
              if (ChronoUnit.MINUTES.between(cart.get(i).getRegDate(), now) >= 30) {
                List<Long> cartItemList = cartService.deleteCartItems(cart.get(i).getId());
                if (!cartItemList.isEmpty()) {
                  for (Long cartItem : cartItemList) {
                    log.info("DELETE CART ITEM ID : {}", cartItem);
                  }
                }
                cartService.deleteCart(cart.get(i).getId());
                log.info("DELETE CART ID : {}", cart.get(i).getId());
              }
            }
          }

        }catch (Exception e){
          log.info(e.getMessage());
        }
        log.info("===========SCHEDULER END=================");
    }
}
