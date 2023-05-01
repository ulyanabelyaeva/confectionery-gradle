package com.belyaeva.confectionerygradle.controller;

import com.belyaeva.confectionerygradle.entity.Cart;
import com.belyaeva.confectionerygradle.services.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.belyaeva.confectionerygradle.services.impl.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final CartServiceImpl cartServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl,
                          CartServiceImpl cartServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
    }

    @GetMapping("/user")
    public String userPage(Model model){
        model.addAttribute("tempUser", userServiceImpl.getTempUser());
        List<Cart> orders = cartServiceImpl.getOrderList(userServiceImpl.getTempUser().getId());
        model.addAttribute("orders", orders);
        return "user";
    }

}
