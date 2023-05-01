package com.belyaeva.confectionerygradle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Model describe cart.
 * Cart has single user.
 *
 * @author Ulyana
 */
@Entity
@Table(name = "cart")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    /**
     * Primary key
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Owner
     * */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Show status
     * false - this is cart
     * true - this is order
     * Cart become order when user clicked "Pay"
     * */
    @Column(name = "status")
    private boolean status;

    /**
     * Show that order is ready
     * false - not ready
     * true - ready
     * Order become ready when admin clicked "Ready"
     * */
    @Column(name = "ready")
    private boolean ready;

    /**
     * Cost of order
     * Cost calculate when user clicked "Pay"
     * */
    @Column(name = "cost")
    private int cost;

    /**
     * Date of order`s creation
     * Init when user clicked "Pay"
     * */
    @Column(name = "date")
    private String date;

    /**
     * Cart items
     * */
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> items;
}
