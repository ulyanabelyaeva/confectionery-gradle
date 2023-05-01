package com.belyaeva.confectionerygradle.repository;

import com.belyaeva.confectionerygradle.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
