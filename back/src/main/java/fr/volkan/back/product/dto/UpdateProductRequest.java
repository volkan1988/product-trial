package fr.volkan.back.product.dto;

import fr.volkan.back.product.domain.InventoryStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateProductRequest {

    private Long id;
    private String code;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String image;

    @NotNull
    private String category;

    @PositiveOrZero
    private double price;

    @Positive
    private int quantity;

    private String internalReference;
    private Long shellId;
    private InventoryStatus inventoryStatus;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
