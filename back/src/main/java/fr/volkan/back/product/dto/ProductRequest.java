package fr.volkan.back.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String category;

    private double price;
}
