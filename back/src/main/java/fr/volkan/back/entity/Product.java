package fr.volkan.back.entity;

import fr.volkan.back.domain.InventoryStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdGenerator")
    @SequenceGenerator(
            name = "productIdGenerator",
            sequenceName = "product_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId;

    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
