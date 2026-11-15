package fr.volkan.back.cart.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItemsResponse {

    private List<CartItemResponse> cartItems;
}
