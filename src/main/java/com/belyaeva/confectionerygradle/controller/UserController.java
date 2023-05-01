package com.belyaeva.confectionerygradle.controller;

import com.belyaeva.confectionerygradle.entity.CartEntity;
import com.belyaeva.confectionerygradle.services.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.belyaeva.confectionerygradle.services.impl.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @GetMapping("/user")
    public String userPage(Model model){
        model.addAttribute("tempUser", userServiceImpl.getTempUser());
        List<CartEntity> orders = cartServiceImpl.getOrderList(userServiceImpl.getTempUser().getId());
        model.addAttribute("orders", orders);
        return "user";
    }

}
