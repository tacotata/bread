package com.example.helloproject.service;


import com.example.helloproject.data.dto.cart.*;
import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.repository.cart.CartItemRepository;
import com.example.helloproject.data.repository.cart.CartRepository;
import com.example.helloproject.data.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UsersRepository usersRepository;

    public Long save(CartSaveRequestDto cartSaveRequestDto){
        return cartRepository.save(cartSaveRequestDto.toEntity()).getId();
    }

    public Long saveCartItem(CartItemSaveRequestDto cartItemSaveRequestDto){
        return cartItemRepository.save(cartItemSaveRequestDto.toEntity()).getId();
    }


    @Transactional(readOnly = true)
    public List<CartItemDetailDto> getCartItemList (String email){
        List <CartItemDetailDto> cartItemDetailDtoList = new ArrayList<>();
        Users users = usersRepository.findByEmail(email);
        Cart cart = cartRepository.findByUserId(users.getId());
            if(cart == null){
            log.info("cart is null");
            return cartItemDetailDtoList;
        }
        cartItemDetailDtoList = cartItemRepository.findCartItemDetailDtoList(cart.getId());
        log.info("cartItemDetailDtoList.size() {}",cartItemDetailDtoList.size());
        return cartItemDetailDtoList;
    }

    @Transactional(readOnly = true)
    public CartDetailDto getCartList(Long cartId){
        return cartRepository.findCartDetailDto(cartId);
    }

   //cart delete
    public Long delete (Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장바구니가 없습니다. id=" + cartId));
        cartRepository.delete(cart);
        return cart.getId();
    }

    //cartItem delete 전체
    @Transactional
    public List<Long> deleteCartItem (Long cartId) {
        List<Long> cartItemIds = cartItemRepository.findByCartItemIds(cartId);
        cartItemRepository.deleteAllByCartItemIds(cartItemIds);
        return cartItemIds;
    }

}
