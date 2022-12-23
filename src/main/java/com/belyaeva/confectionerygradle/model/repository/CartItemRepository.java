package com.belyaeva.confectionerygradle.model.repository;

import com.belyaeva.confectionerygradle.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
