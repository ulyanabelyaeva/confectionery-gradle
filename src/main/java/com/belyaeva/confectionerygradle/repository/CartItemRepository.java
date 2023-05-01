package com.belyaeva.confectionerygradle.repository;

import com.belyaeva.confectionerygradle.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
