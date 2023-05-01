package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.ProductTypeEntity;
import com.belyaeva.confectionerygradle.repository.ProductTypeRepository;
import com.belyaeva.confectionerygradle.services.abstractions.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductTypeEntity> getProductTypeList(){
        return productTypeRepository.findAll();
    }


    public ProductTypeEntity getProductTypeByName(String name){
        return productTypeRepository.findByName(name).get(0);
    }
}
