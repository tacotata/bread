package com.example.helloproject.service;


import com.example.helloproject.data.dto.cart.CartItemSaveRequestDto;
import com.example.helloproject.data.dto.cart.CartSaveRequestDto;
import com.example.helloproject.data.entity.cart.CartItem;
import com.example.helloproject.data.repository.cart.CartItemRepository;
import com.example.helloproject.data.repository.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Long save(CartSaveRequestDto cartSaveRequestDto){
        return cartRepository.save(cartSaveRequestDto.toEntity()).getId();
    }

    public Long saveCartItem(CartItemSaveRequestDto cartItemSaveRequestDto){
        return cartItemRepository.save(cartItemSaveRequestDto.toEntity()).getId();
    }

}
