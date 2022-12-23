package com.belyaeva.confectionerygradle.model.repository;

import com.belyaeva.confectionerygradle.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    /*List<CartEntity> findAllByUserId(Long id);*/
    List<CartEntity> findAllByUserId(Long id);
}
