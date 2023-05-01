package com.belyaeva.confectionerygradle.repository;

import com.belyaeva.confectionerygradle.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    //@Query("SELECT p FROM ProductEntity p WHERE p.productType.id = :param")
    List<ProductEntity> findAllByProductTypeId(Long id);
}
