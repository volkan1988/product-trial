package fr.volkan.back.product.entity;

import fr.volkan.back.product.domain.InventoryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdGenerator")
    @SequenceGenerator(
            name = "productIdGenerator",
            sequenceName = "product_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(unique = true, nullable = false, length = 9)
    private String code;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String image;

    @NotNull
    private String category;

    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId;

    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    private Integer rating;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
