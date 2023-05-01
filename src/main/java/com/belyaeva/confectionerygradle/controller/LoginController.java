package com.belyaeva.confectionerygradle.controller;

import com.belyaeva.confectionerygradle.entity.Cart;
import com.belyaeva.confectionerygradle.entity.User;
import com.belyaeva.confectionerygradle.services.impl.CartServiceImpl;
import com.belyaeva.confectionerygradle.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    private final UserServiceImpl userServiceImpl;

    private final CartServiceImpl cartServiceImpl;

    @Autowired
    public LoginController(UserServiceImpl userServiceImpl,
                           CartServiceImpl cartServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
    }

    @GetMapping("/login")
    public String log(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/reg")
    public String reg(){
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(@ModelAttribute("user") User user,
                          HttpSession session) {

        if (!user.getPassword().equals(user.getPasswordConfirm())){
            session.setAttribute("userError", "Пароли не совпадают");
            return "redirect:/reg";
        }

        if (!userServiceImpl.saveUser(user)){
            session.setAttribute("userError", "Пользователь с таким телефоном уже существует");
            return "redirect:/reg";
        } else {
            cartServiceImpl.addNewCart(Cart.builder()
                    .user(user)
                    .status(false)
                    .ready(false)
                    .cost(0)
                    .build());
            return "redirect:/login";
        }

    }
}