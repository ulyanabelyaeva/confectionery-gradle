package com.belyaeva.confectionerygradle.model.repository;

import com.belyaeva.confectionerygradle.model.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {
    List<ProductTypeEntity> findByName(String name);
}
