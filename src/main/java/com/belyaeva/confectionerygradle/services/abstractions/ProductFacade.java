package com.belyaeva.confectionerygradle.services.abstractions;

public interface ProductFacade<T, U> {
    T getProductsAndUser(U model);

    T getProductsByTypeAndUser(Long id, U model);
}
