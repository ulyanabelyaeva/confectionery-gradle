package com.belyaeva.confectionerygradle.repository;

import com.belyaeva.confectionerygradle.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    /*List<CartEntity> findAllByUserId(Long id);*/
    List<CartEntity> findAllByUserId(Long id);
}
