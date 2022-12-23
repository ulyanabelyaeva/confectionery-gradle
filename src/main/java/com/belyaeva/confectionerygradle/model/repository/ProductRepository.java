package com.belyaeva.confectionerygradle.model.repository;

import com.belyaeva.confectionerygradle.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    //@Query("SELECT p FROM ProductEntity p WHERE p.productType.id = :param")
    List<ProductEntity> findAllByProductTypeId(Long id);
}
