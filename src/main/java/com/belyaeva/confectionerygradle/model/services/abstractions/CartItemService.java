package com.belyaeva.confectionerygradle.model.services.abstractions;

import com.belyaeva.confectionerygradle.model.entity.CartItemEntity;

public interface CartItemService {

    CartItemEntity getItemById(Long id);

    void addNewItem(CartItemEntity cartItemEntity);

    void deleteItemById(Long id);

}
