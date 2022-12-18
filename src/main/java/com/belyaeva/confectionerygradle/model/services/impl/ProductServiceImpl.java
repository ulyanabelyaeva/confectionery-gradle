package com.belyaeva.confectionerygradle.model.services.impl;

import com.belyaeva.confectionerygradle.model.entity.ProductEntity;
import com.belyaeva.confectionerygradle.model.repository.ProductRepository;
import com.belyaeva.confectionerygradle.model.services.StorageProperties;
import com.belyaeva.confectionerygradle.model.services.abstractions.ProductService;
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

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeServiceImpl productTypeServiceImpl;

    @Autowired
    private StorageProperties storageProperties;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll().stream()
            .filter(ProductEntity::isStatus)
            .collect(Collectors.toList());
    }
    public List<ProductEntity> getProductByProductTypeId(Long id){
        List<ProductEntity> productEntities;
        if (id == 1){
            productEntities = getAllProducts();
        } else {
            productEntities = productRepository.findProductEntitiesByProductTypeId(id).stream()
                .filter(ProductEntity::isStatus)
                .collect(Collectors.toList());
        }
        return productEntities;
    }

    public ProductEntity getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    private ProductEntity createImage(ProductEntity productEntity){
        String name = productEntity.getIcon().getOriginalFilename();
        String filePath = System.getProperty("user.dir") + Paths.get(storageProperties.getLocation()) + "\\" + name;
        try {
            File newImage = new File(filePath);
            productEntity.getIcon().transferTo(newImage);
            productEntity.setImage(productEntity.getIcon().getOriginalFilename());
            productEntity.setIcon(null);
        } catch (IOException e) {
            logger.info("не удалось создать файл");
        }
        return productEntity;
    }

    public ProductEntity addNewProduct(ProductEntity product){
        createImage(product);
        product.setProductType(productTypeServiceImpl.getProductTypeByName(product.getNameProductType()));
        product.setStatus(true);
        return productRepository.save(product);
    }

    public ProductEntity changeProduct(ProductEntity product){
        if (isNewImage(product)){
            product.setImage(productRepository.findById(product.getId()).get().getImage());
        } else {
            createImage(product);
        }
        product.setProductType(productTypeServiceImpl.getProductTypeByName(product.getNameProductType()));
        product.setStatus(true);
        return productRepository.save(product);
    }

    private boolean isNewImage(ProductEntity product){
        return product.getIcon().getOriginalFilename().equals("");
    }

    public void deleteProduct(Long id){
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        productEntity.setStatus(false);
        productRepository.save(productEntity);
    }
}
