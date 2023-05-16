package com.example.helloproject.data.repository.cart;

import com.example.helloproject.data.dto.cart.CartDetailDto;
import com.example.helloproject.data.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select new com.example.helloproject.data.dto.cart.CartDetailDto(c.id, c.reservedDate, c.reservedTime, s.id, s.name)" +
            "from Cart c " +
            "join c.store s " +
            "where c.id = :cartId " +
            "and c.store.id = s.id"
    )
    CartDetailDto findCartDetailDto(@Param("cartId") Long cartId);

    @Query("SELECT c FROM Cart c WHERE c.users.id = ?1")
    Cart findByUserId(Long userId);
}
