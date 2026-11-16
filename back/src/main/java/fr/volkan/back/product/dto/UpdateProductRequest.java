package fr.volkan.back.product.dto;

import fr.volkan.back.product.domain.InventoryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String image;

    @NotBlank
    private String category;

    @PositiveOrZero
    private double price;

    @PositiveOrZero
    private int quantity;

    private String internalReference;
    private Long shellId;
    private InventoryStatus inventoryStatus;
    private int rating;
}
