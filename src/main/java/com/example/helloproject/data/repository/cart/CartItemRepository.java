package com.example.helloproject.data.repository.cart;

import com.example.helloproject.data.dto.cart.CartItemDetailDto;
import com.example.helloproject.data.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("select new com.example.helloproject.data.dto.cart.CartItemDetailDto(ci.id, ci.cart.id, i.name, i.price, ci.count, if.fileName, if.updDate)" +
            "from CartItem ci , ItemsFile if " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and if.items.id = ci.item.id " +
            "order by ci.regDate desc"
    )
    List<CartItemDetailDto> findCartItemDetailDtoList(@Param("cartId") Long cartId);


    @Query("SELECT id FROM CartItem n WHERE n.cart.id = ?1")
    List<Long> findByCartItemIds(Long cartId);

    @Modifying(clearAutomatically = true)
    @Query("delete from CartItem n where n.id in :ids")
    void deleteAllByCartItemIds(@Param("ids") List<Long> ids);
}
