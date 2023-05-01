package com.belyaeva.confectionerygradle.services.abstractions;

import org.springframework.ui.Model;

public interface ProductTempUserFacade {
    void getProductsAndUser(Model model);

    void getProductsByTypeAndUser(Long id, Model model);
}
