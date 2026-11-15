package fr.volkan.back.cart.entity;

import fr.volkan.back.product.entity.Product;
import fr.volkan.back.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartItemIdGenerator")
    @SequenceGenerator(
            name = "cartItemIdGenerator",
            sequenceName = "cart_item_id_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Positive
    private int quantity;
}
