package com.belyaeva.confectionerygradle.services.abstractions;

import com.belyaeva.confectionerygradle.entity.ProductTypeEntity;

import java.util.List;

public interface ProductTypeService {

    List<ProductTypeEntity> getProductTypeList();

    ProductTypeEntity getProductTypeByName(String name);

}
