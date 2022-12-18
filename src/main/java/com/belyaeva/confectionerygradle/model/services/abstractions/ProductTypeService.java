package com.belyaeva.confectionerygradle.model.services.abstractions;

import com.belyaeva.confectionerygradle.model.entity.ProductTypeEntity;

import java.util.List;

public interface ProductTypeService {

    List<ProductTypeEntity> getProductTypeList();

    ProductTypeEntity getProductTypeByName(String name);

}
