package com.belyaeva.confectionerygradle.model.repository;

import com.belyaeva.confectionerygradle.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findProductEntitiesByProductTypeId(Long id);
}
