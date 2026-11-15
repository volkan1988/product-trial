package fr.volkan.back.cart.dto;

import fr.volkan.back.product.dto.ProductResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {

    private ProductResponse product;
    private int quantity;
}
