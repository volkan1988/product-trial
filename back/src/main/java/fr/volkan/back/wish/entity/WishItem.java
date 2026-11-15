package fr.volkan.back.wish.entity;

import fr.volkan.back.product.entity.Product;
import fr.volkan.back.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WishItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishItemIdGenerator")
    @SequenceGenerator(
            name = "wishItemIdGenerator",
            sequenceName = "wish_item_id_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
