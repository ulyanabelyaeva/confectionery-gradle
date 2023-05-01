package com.belyaeva.confectionerygradle.services.abstractions;

import com.belyaeva.confectionerygradle.entity.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> getProductTypeList();

    ProductType getProductTypeByName(String name);

}
