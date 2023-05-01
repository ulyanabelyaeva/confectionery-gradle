package com.belyaeva.confectionerygradle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "CartEntity.findAllByUserId",
        query = "select c from CartEntity c where c.user.id = ?1")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "status")
    private boolean status;

    @Column(name = "ready")
    private boolean ready;

    @Column(name = "cost")
    private int cost;

    @Column(name = "date")
    private String date;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItemEntity> items;
}
