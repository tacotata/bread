package com.example.helloproject.service;

import com.example.helloproject.data.dto.orders.OrderDto;
import com.example.helloproject.data.entity.cart.Cart;
import com.example.helloproject.data.entity.cart.CartItem;
import com.example.helloproject.data.entity.orders.OrderItem;
import com.example.helloproject.data.entity.orders.Orders;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.repository.cart.CartItemRepository;
import com.example.helloproject.data.repository.cart.CartRepository;
import com.example.helloproject.data.repository.orders.OrdersRepository;
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
public class OrderService {

    private final CartItemRepository cartItemRepository;
    private final UsersRepository usersRepository;
    private final OrdersRepository ordersRepository;
    private final CartRepository cartRepository;

    //orderItem, order save
    public Long orders(List<OrderDto> orderDtoList, String email, Long cartId){
        Users users = usersRepository.findByEmail(email);
        List<OrderItem> orderItemList  = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            CartItem cartItem = cartItemRepository.findById(orderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderItem orderItem = OrderItem.builder().item(cartItem.getItem()).count(cartItem.getCount()).orderPrice(cartItem.getItem().getPrice()).build();
            orderItemList.add(orderItem);
        }
        Cart cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        Orders orders = Orders.createOrders(users, orderItemList, cart);
        ordersRepository.save(orders);
        return orders.getId();
    }

}
