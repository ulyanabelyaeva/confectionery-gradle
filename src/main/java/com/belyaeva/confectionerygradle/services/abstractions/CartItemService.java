package com.belyaeva.confectionerygradle.services.abstractions;

import com.belyaeva.confectionerygradle.entity.CartItem;

public interface CartItemService {

    CartItem getItemById(Long id);

    void addNewItem(CartItem cartItem);

    void deleteItemById(Long id);

}
