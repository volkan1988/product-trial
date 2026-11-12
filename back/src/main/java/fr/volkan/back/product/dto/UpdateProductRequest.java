package fr.volkan.back.product.dto;

import fr.volkan.back.product.domain.InventoryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateProductRequest {

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
    private InventoryStatus inventoryStatus;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
