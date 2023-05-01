package com.belyaeva.confectionerygradle.controller;

import com.belyaeva.confectionerygradle.entity.Cart;
import com.belyaeva.confectionerygradle.entity.CartItem;
import com.belyaeva.confectionerygradle.entity.Product;
import com.belyaeva.confectionerygradle.entity.Role;
import com.belyaeva.confectionerygradle.entity.User;
import com.belyaeva.confectionerygradle.services.abstractions.ProductTempUserFacade;
import com.belyaeva.confectionerygradle.services.impl.CartItemServiceImpl;
import com.belyaeva.confectionerygradle.services.impl.CartServiceImpl;
import com.belyaeva.confectionerygradle.services.impl.ProductServiceImpl;
import com.belyaeva.confectionerygradle.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final ProductTempUserFacade productTempUserFacade;

    private final CartItemServiceImpl cartItemServiceImpl;

    private final UserServiceImpl userServiceImpl;

    private final ProductServiceImpl productServiceImpl;

    private final CartServiceImpl cartServiceImpl;

    @Autowired
    public MainController(ProductTempUserFacade productTempUserFacade,
                          CartItemServiceImpl cartItemServiceImpl,
                          UserServiceImpl userServiceImpl,
                          ProductServiceImpl productServiceImpl,
                          CartServiceImpl cartServiceImpl) {
        this.productTempUserFacade = productTempUserFacade;
        this.cartItemServiceImpl = cartItemServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.productServiceImpl = productServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
    }

    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("tempUser", userServiceImpl.getTempUser());
        return "index";
    }

    @GetMapping("/catalog")
    public String getProducts(Model model){
        productTempUserFacade.getProductsAndUser(model);
        return isAdmin() ? "redirect:/admin/catalog" : "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getProductByProductTypeId(@PathVariable("id") Long id,
                                            Model model){
        productTempUserFacade.getProductsByTypeAndUser(id, model);
        return isAdmin() ? "redirect:/admin/catalog" : "catalog";
    }

    private boolean isAdmin() {
        User user = userServiceImpl.getTempUser();
        if (user == null) {
            return false;
        }
        Role role = user.getRoles().stream()
            .filter(r -> r.getName().equals("USER"))
            .findFirst()
            .orElse(null);
        return role == null;
    }

    @GetMapping("/catalog/catalog/{id}")
    public String redirectCatalog(@PathVariable("id") Long id){
        return "redirect:/catalog/{id}";
    }

    @PostMapping("/catalog/{id}")
    public String addToCartFromCatalogByProductType(@RequestParam("btn") String btn,
                                                    HttpSession session){

        if (userServiceImpl.getTempUser() == null){
            session.setAttribute("error", "Чтобы добавлять товары в корзину необходимо авторизоваться");
            return "redirect:/catalog/{id}";
        }
        session.setAttribute("error", "");
        addNewItemToCart(btn);
        return "redirect:/catalog";
    }

    @PostMapping("/catalog")
    public String addToCart(@RequestParam("btn") String btn,
                            HttpSession session){

        if (userServiceImpl.getTempUser() == null){
            session.setAttribute("error", "Чтобы добавлять товары в корзину необходимо авторизоваться");
            return "redirect:/catalog";
        }
        session.setAttribute("error", "");
        addNewItemToCart(btn);
        return "redirect:/catalog";
    }

    private void addNewItemToCart(String id){
        Long idProduct = Long.parseLong(id);
        Product product = productServiceImpl.getProductById(idProduct);
        Cart cart = cartServiceImpl.getCartByUserId(userServiceImpl.getTempUser().getId());
        cart.setCost(cart.getCost() + product.getPrice());
        CartItem cartItem = CartItem.builder()
                .product(product)
                .cart(cart)
                .build();
        cartItemServiceImpl.addNewItem(cartItem);
    }

    @GetMapping("/catalog/cart")
    public String toCart(){
        return "redirect:/user/cart";
    }
}
