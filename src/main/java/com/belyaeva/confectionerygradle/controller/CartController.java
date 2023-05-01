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

/**
 * Controller for action with user`s cart
 *
 * @author Ulyana
 * */
@Controller
public class CartController {

    private final UserServiceImpl userServiceImpl;

    private final CartServiceImpl cartServiceImpl;

    private final CartItemServiceImpl cartItemServiceImpl;

    @Autowired
    public CartController(UserServiceImpl userServiceImpl,
                          CartServiceImpl cartServiceImpl,
                          CartItemServiceImpl cartItemServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
        this.cartItemServiceImpl = cartItemServiceImpl;
    }

    /**
     * Method for get cart using current authenticated user
     *
     * @param model contain data to render on view
     * @return view 'Cart'
     * */
    @GetMapping("/user/cart")
    public String getCart(Model model){

        User user = userServiceImpl.getTempUser();

        Cart cart = cartServiceImpl.getCartByUserId(user.getId());
        model.addAttribute("tempUser", user);
        model.addAttribute("cart", cart);

        return "cart";
    }

    /**
     *  Method for delete item from cart and migrate cart to order
     *
     * @param model contain data to render on view
     * @param btn button that user clicked
     *            if this is 'Pay' button, cart is migrated to order and new cart ia created
     *            if this is 'Delete' button, cart item is being deleted
     * @return redirect to same view
     * */
    @PostMapping("/user/cart")
    public String deleteItem(@RequestParam("btn") String btn,
                             Model model){
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
