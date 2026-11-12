package fr.volkan.back.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private String description;
    private String category;
    private double price;
}
