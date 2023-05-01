package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.Product;
import com.belyaeva.confectionerygradle.repository.ProductRepository;
import com.belyaeva.confectionerygradle.services.StorageProperties;
import com.belyaeva.confectionerygradle.services.abstractions.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final ProductTypeServiceImpl productTypeServiceImpl;

    private final StorageProperties storageProperties;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductTypeServiceImpl productTypeServiceImpl,
                              StorageProperties storageProperties) {
        this.productRepository = productRepository;
        this.productTypeServiceImpl = productTypeServiceImpl;
        this.storageProperties = storageProperties;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll().stream()
            .filter(Product::isStatus)
            .collect(Collectors.toList());
    }


    public List<Product> getProductByProductTypeId(Long id){
        List<Product> productEntities;
        if (id == 1){
            productEntities = getAllProducts();
        } else {
            productEntities = productRepository.findAllByProductTypeId(id).stream()
                .filter(Product::isStatus)
                .collect(Collectors.toList());
        }
        return productEntities;
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    private Product createImage(Product product){
        String name = product.getIcon().getOriginalFilename();
        String filePath = System.getProperty("user.dir") + Paths.get(storageProperties.getLocation()) + "\\" + name;
        try {
            File newImage = new File(filePath);
            product.getIcon().transferTo(newImage);
            product.setImage(product.getIcon().getOriginalFilename());
            product.setIcon(null);
        } catch (IOException e) {
            logger.info("не удалось создать файл");
        }
        return product;
    }

    public Product addNewProduct(Product product){
        createImage(product);
        product.setProductType(productTypeServiceImpl.getProductTypeByName(product.getNameProductType()));
        product.setStatus(true);
        return productRepository.save(product);
    }

    public Product changeProduct(Product product){
        if (isNewImage(product)){
            product.setImage(productRepository.findById(product.getId()).get().getImage());
        } else {
            createImage(product);
        }
        product.setProductType(productTypeServiceImpl.getProductTypeByName(product.getNameProductType()));
        product.setStatus(true);
        return productRepository.save(product);
    }

    private boolean isNewImage(Product product){
        return product.getIcon().getOriginalFilename().equals("");
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElse(null);
        product.setStatus(false);
        productRepository.save(product);
    }
}
