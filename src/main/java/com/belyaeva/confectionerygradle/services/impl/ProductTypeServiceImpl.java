package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.ProductType;
import com.belyaeva.confectionerygradle.repository.ProductTypeRepository;
import com.belyaeva.confectionerygradle.services.abstractions.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> getProductTypeList(){
        return productTypeRepository.findAll();
    }


    public ProductType getProductTypeByName(String name){
        return productTypeRepository.findByName(name).get(0);
    }
}
