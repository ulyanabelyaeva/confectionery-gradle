package com.belyaeva.confectionerygradle.controller;

import com.belyaeva.confectionerygradle.entity.Cart;
import com.belyaeva.confectionerygradle.entity.Product;
import com.belyaeva.confectionerygradle.services.abstractions.ProductTempUserFacade;
import com.belyaeva.confectionerygradle.services.impl.CartServiceImpl;
import com.belyaeva.confectionerygradle.services.impl.ProductServiceImpl;
import com.belyaeva.confectionerygradle.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for admin actions
 *
 * @author Ulyana
 * */
@Controller
public class AdminController {

    private final CartServiceImpl cartServiceImpl;

    private final UserServiceImpl userServiceImpl;

    private final ProductTempUserFacade productTempUserFacade;

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public AdminController(CartServiceImpl cartServiceImpl,
                           UserServiceImpl userServiceImpl,
                           ProductTempUserFacade productTempUserFacade,
                           ProductServiceImpl productServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.productTempUserFacade = productTempUserFacade;
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("tempUser", userServiceImpl.getTempUser());
        List<Cart> orders = cartServiceImpl.getUnreadyOrderList();
        model.addAttribute("orders", orders);
        return "admin";
    }

    @PostMapping("/admin")
    public String reformOrder(@RequestParam("btn") String btn,
                              @RequestParam("cartId") Long cartId){
        if (btn.equals("ready")){
            Cart cart = cartServiceImpl.getCartById(cartId);
            cartServiceImpl.moveOrderToReady(cart);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/catalog")
    public String getAdminCatalog(@RequestParam(defaultValue="-1") Long id,
                                  Model model){
        productTempUserFacade.getProductsAndUser(model);
        showPutOrAdd(id, model);
        return "catalog_admin";
    }
    @GetMapping("/admin/catalog/{id}")
    public String getAdminCatalogByProductTypeId(@RequestParam(defaultValue="-1") Long id,
                                                 @PathVariable("id") Long idSearch,
                                                 Model model){
        productTempUserFacade.getProductsByTypeAndUser(idSearch, model);
        showPutOrAdd(id,model);
        return "catalog_admin";
    }

    private void showPutOrAdd(Long id,
                              Model model) {
        if (id != -1){
            Product product = productServiceImpl.getProductById(id);
            product.setNameProductType(product.getProductType().getName());
            model.addAttribute("put_product", product);
        }
        else{
            model.addAttribute("add_product", new Product());
            model.addAttribute("put_product", null);
        }
    }

    @GetMapping("/admin/catalog/catalog/{id}")
    public String redirectCatalog(@PathVariable("id") Long id){
        return "redirect:/admin/catalog/{id}";
    }

    @PostMapping("/admin/catalog")
    public String addNewProduct(@ModelAttribute("add_product") Product product){
        productServiceImpl.addNewProduct(product);
        return "redirect:/admin/catalog";
    }

    @PostMapping("/admin/catalog/{id}")
    public String addNewProductIfOnProductTypeChoice(@ModelAttribute("add_product") Product product){
        productServiceImpl.addNewProduct(product);
        return "redirect:/admin/catalog";
    }

    @DeleteMapping("/admin/catalog")
    public String deleteItem(@RequestParam("btn_del") Long id){
        productServiceImpl.deleteProduct(id);
        return "redirect:/admin/catalog";
    }

    @DeleteMapping("/admin/catalog/{id}")
    public String deleteItemIfOnProductTypeChoice(@RequestParam("btn_del") Long id){
        productServiceImpl.deleteProduct(id);
        return "redirect:/admin/catalog/{id}";
    }

    @PutMapping("/admin/catalog")
    public String putProduct(@ModelAttribute("put_product") Product product){
        productServiceImpl.changeProduct(product);
        return "redirect:/admin/catalog";
    }

    @PutMapping("/admin/catalog/{id}")
    public String putProductIfOnProductTypeChoice(@ModelAttribute("put_product") Product product){
        productServiceImpl.changeProduct(product);
        return "redirect:/admin/catalog/{id}";
    }

}
