package com.training.online_shopping.repository;

import com.training.online_shopping.model.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardLineRepository extends JpaRepository<CartLine,Integer> {

    @Query(value ="FROM CartLine WHERE cartId = ?1 AND productId =?2",nativeQuery = false)
    CartLine getByCartAndProductId(int id, int productId);

    @Query(value ="FROM CartLine WHERE cartId =?1",nativeQuery = false)
    List<CartLine> list(int id);

}
