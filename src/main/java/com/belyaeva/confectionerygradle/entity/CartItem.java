package com.belyaeva.confectionerygradle.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Model describe cart item.
 * It contains link to cart and product.
 *
 * @author Ulyana
 */
@Entity
@Table(name = "cartItem")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
