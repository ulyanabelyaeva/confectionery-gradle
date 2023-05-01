package com.belyaeva.confectionerygradle.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@NamedQuery(name = "ProductEntity.findAllByProductTypeId",
    query = "select p from ProductEntity p where p.productType.id = ?1")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = ProductTypeEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductTypeEntity productType;

    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private boolean status;

    @Transient
    private String nameProductType;

    @Transient
    private MultipartFile icon;
}
