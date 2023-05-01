package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.CartItem;
import com.belyaeva.confectionerygradle.repository.CartItemRepository;
import com.belyaeva.confectionerygradle.services.abstractions.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem getItemById(Long id){
        return cartItemRepository.findById(id).orElse(null);
    }

    public void addNewItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    public void deleteItemById(Long id){
        cartItemRepository.deleteById(id);
    }
}
