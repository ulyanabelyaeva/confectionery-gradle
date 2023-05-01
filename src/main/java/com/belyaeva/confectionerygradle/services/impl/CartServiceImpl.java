package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.Cart;
import com.belyaeva.confectionerygradle.services.abstractions.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.belyaeva.confectionerygradle.repository.CartRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    public static final SimpleDateFormat TEXT_FORMATTER = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private final Logger logger = Logger.getLogger(CartServiceImpl.class.getName());

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public Cart getCartByUserId(Long id){
        return cartRepository.findAllByUserId(id).stream()
                .filter(c -> !c.isStatus())
                .findFirst()
                .orElse(null);
    }


    public List<Cart> getOrderList(Long id){
        return cartRepository.findAllByUserId(id).stream()
                .filter(Cart::isStatus)
                .sorted(((Comparator<Cart>) (o1, o2) -> {
                    try {
                        return TEXT_FORMATTER.parse(o1.getDate()).compareTo(TEXT_FORMATTER.parse(o2.getDate()));
                    } catch (ParseException e) {
                        logger.info("не удалось распарсить дату");
                        return 0;
                    }
                }).reversed())
                .collect(Collectors.toList());
    }

    public List<Cart> getUnreadyOrderList(){
        return cartRepository.findAll().stream()
                .filter(c -> !c.isReady() && c.isStatus())
                .collect(Collectors.toList());
    }

    public void addNewCart(Cart cart){
        cartRepository.save(cart);
    }

    public void moveOldCartToOrdersAndCreteNewCart(Cart cart){
        cart.setDate(TEXT_FORMATTER.format(new Date()));
        cart.setStatus(true);
        cartRepository.save(cart);
    }

    public void moveOrderToReady(Cart cart){
        cart.setReady(true);
        cartRepository.save(cart);
    }

    public Cart getCartById(Long id){
        return cartRepository.findById(id).orElse(null);
    }

}
