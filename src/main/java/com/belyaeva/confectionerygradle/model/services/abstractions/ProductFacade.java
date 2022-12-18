package com.belyaeva.confectionerygradle.model.services.abstractions;

public interface ProductFacade<T, U> {
    T getProductsAndUser(U model);

    T getProductsByTypeAndUser(Long id, U model);
}
