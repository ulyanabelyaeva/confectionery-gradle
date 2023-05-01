package com.belyaeva.confectionerygradle.repository;

import com.belyaeva.confectionerygradle.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductTypeId(Long id);
}
