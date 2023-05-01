package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.Product;
import com.belyaeva.confectionerygradle.entity.ProductType;
import com.belyaeva.confectionerygradle.entity.User;
import com.belyaeva.confectionerygradle.services.abstractions.ProductTempUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ProductTempUserFacadeImpl implements ProductTempUserFacade {

    private final ProductTypeServiceImpl productTypeServiceImpl;

    private final ProductServiceImpl productServiceImpl;

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public ProductTempUserFacadeImpl(ProductTypeServiceImpl productTypeServiceImpl,
                                     ProductServiceImpl productServiceImpl,
                                     UserServiceImpl userServiceImpl) {
        this.productTypeServiceImpl = productTypeServiceImpl;
        this.productServiceImpl = productServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    public void getProductsAndUser(Model model) {
        List<ProductType> productTypeList = productTypeServiceImpl.getProductTypeList();
        List<Product> productList = productServiceImpl.getAllProducts();
        User user = userServiceImpl.getTempUser();
        model.addAttribute("tempUser", user);
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("products", productList);
    }

    public void getProductsByTypeAndUser(Long id, Model model){
        getProductsAndUser(model);
        List<Product> productList = productServiceImpl.getProductByProductTypeId(id);
        model.addAttribute("products", productList);
    }
}
