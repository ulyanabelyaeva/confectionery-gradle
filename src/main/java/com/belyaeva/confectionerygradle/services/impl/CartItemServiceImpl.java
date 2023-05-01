package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.CartItemEntity;
import com.belyaeva.confectionerygradle.repository.CartItemRepository;
import com.belyaeva.confectionerygradle.services.abstractions.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItemEntity getItemById(Long id){
        return cartItemRepository.findById(id).orElse(null);
    }

    public void addNewItem(CartItemEntity cartItemEntity){
        cartItemRepository.save(cartItemEntity);
    }

    public void deleteItemById(Long id){
        cartItemRepository.deleteById(id);
    }
}
