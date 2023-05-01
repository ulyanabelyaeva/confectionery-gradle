package com.belyaeva.confectionerygradle.services.abstractions;

import com.belyaeva.confectionerygradle.entity.CartItemEntity;

public interface CartItemService {

    CartItemEntity getItemById(Long id);

    void addNewItem(CartItemEntity cartItemEntity);

    void deleteItemById(Long id);

}
