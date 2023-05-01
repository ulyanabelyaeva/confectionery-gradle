package com.belyaeva.confectionerygradle.controller;

import com.belyaeva.confectionerygradle.entity.Cart;
import com.belyaeva.confectionerygradle.services.impl.CartItemServiceImpl;
import com.belyaeva.confectionerygradle.services.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.belyaeva.confectionerygradle.services.impl.UserServiceImpl;
import com.belyaeva.confectionerygradle.entity.User;

@Controller
public class CartController {

    private final UserServiceImpl userServiceImpl;

    private final CartServiceImpl cartServiceImpl;

    private final CartItemServiceImpl cartItemServiceImpl;

    @Autowired
    public CartController(UserServiceImpl userServiceImpl, CartServiceImpl cartServiceImpl, CartItemServiceImpl cartItemServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
        this.cartItemServiceImpl = cartItemServiceImpl;
    }

    @GetMapping("/user/cart")
    public String getCart(Model model){

        User user = userServiceImpl.getTempUser();

        Cart cart = cartServiceImpl.getCartByUserId(user.getId());
        model.addAttribute("tempUser", user);
        model.addAttribute("cart", cart);

        return "cart";
    }

    @PostMapping("/user/cart")
    public String deleteItem(@RequestParam("btn") String btn, Model model){
        if (btn.equals("pay")){
            User user = userServiceImpl.getTempUser();
            Cart cart = cartServiceImpl.getCartByUserId(user.getId());
            cartServiceImpl.moveOldCartToOrdersAndCreteNewCart(cart);
            cartServiceImpl.addNewCart(Cart.builder()
                    .user(user)
                    .status(false)
                    .ready(false)
                    .cost(0)
                    .build());
            model.addAttribute("tempUser", user);
        } else {
            Long idItem = Long.parseLong(btn);
            Cart cart = cartServiceImpl.getCartByUserId(userServiceImpl.getTempUser().getId());
            cart.setCost(cart.getCost() - cartItemServiceImpl.getItemById(idItem).getProduct().getPrice());
            cartItemServiceImpl.deleteItemById(idItem);
        }
        return "redirect:/user/cart";
    }
}
