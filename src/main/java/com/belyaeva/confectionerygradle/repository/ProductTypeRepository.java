package com.belyaeva.confectionerygradle.repository;

import com.belyaeva.confectionerygradle.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    List<ProductType> findByName(String name);
}
