package com.belyaeva.confectionerygradle.services.abstractions;

import com.belyaeva.confectionerygradle.entity.CartEntity;

import java.util.List;

public interface CartService {

    CartEntity getCartByUserId(Long id);

    CartEntity getCartById(Long id);

    void addNewCart(CartEntity cartEntity);

    List<CartEntity> getOrderList(Long id);

    List<CartEntity> getUnreadyOrderList();

    void moveOldCartToOrdersAndCreteNewCart(CartEntity cartEntity);

    void moveOrderToReady(CartEntity cartEntity);
}
