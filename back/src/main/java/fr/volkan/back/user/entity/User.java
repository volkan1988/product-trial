package fr.volkan.back.user.entity;

import fr.volkan.back.cart.entity.CartItem;
import fr.volkan.back.wish.entity.WishItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    @SequenceGenerator(
            name = "userIdGenerator",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String firstname;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "user")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<WishItem> wishItems = new ArrayList<>();

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
