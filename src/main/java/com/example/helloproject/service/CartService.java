package com.example.helloproject.service;


import com.example.helloproject.data.dto.cart.*;
import com.example.helloproject.data.dto.orders.OrderDto;
import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.cart.CartItem;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.repository.cart.CartItemRepository;
import com.example.helloproject.data.repository.cart.CartRepository;
import com.example.helloproject.data.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    private final OrderService orderService;

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
        return cartItemDetailDtoList;
    }

    @Transactional(readOnly = true)
    public CartDetailDto getCartList(Long cartId){
        return cartRepository.findCartDetailDto(cartId);
    }

   //cart delete
    public Long deleteCart (Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장바구니가 없습니다. id=" + cartId));
        cartRepository.delete(cart);
        return cart.getId();
    }

    //cartItem delete 전체
    @Transactional
    public List<Long> deleteCartItems (Long cartId) {
        List<Long> cartItemIds = cartItemRepository.findByCartItemIds(cartId);
        cartItemRepository.deleteAllByCartItemIds(cartItemIds);
        return cartItemIds;
    }

    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email, Long cartId){
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderDto orderDto = new OrderDto();
            orderDto.setItemId(cartItem.getItem().getId());
            orderDto.setCartItemId(cartItem.getId());
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);
        }
        // orderItem , order save
        Long orderId = orderService.orders(orderDtoList, email, cartId);
       //cart, cartItem 삭제
        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }
        Long deletedCartId = this.deleteCart(cartId);
        log.info("DELETE CART ID : {}", deletedCartId);
        return orderId;
    }
    @Transactional(readOnly = true)
    public Long findByUserId(Long userId) {
        Cart cart  =  cartRepository.findByUserId(userId);
        if(cart == null){
            log.info("cart is null");
            return 0L;
        }
        return cart.getId();
    }

    @Transactional(readOnly = true)
    public CartResponseDto findById (Long id){
        Cart entity = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 장바구니가 없습니다. id=" + id));
        return new CartResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Long getCartItem(Long cartId, Long itemId) {
        Long id = cartItemRepository.findByCartIdAndItemId(cartId, itemId);
        if(id == null){
            log.info("item is null");
            return 0L;
        }
        return id;
    }

    @Transactional
    public Long updateCartItem(Long id, CartItemUpdateRequestDto requestDto){
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다. id=" + id));
        cartItem.update(requestDto.getCart(), requestDto.getItem(), requestDto.getCount());
        return id;
    }

    //cart item 단건 삭제
    @Transactional
    public void deleteCartItem (Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다. cartItemId =" + cartItemId ));
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    public Long updateCartItemCnt(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다. cartItemId=" + cartItemId));
        cartItem.updateCnt(count);
        return cartItemId;
    }
}
