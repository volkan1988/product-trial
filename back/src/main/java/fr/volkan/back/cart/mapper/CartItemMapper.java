package fr.volkan.back.cart.mapper;

import fr.volkan.back.cart.dto.CartItemResponse;
import fr.volkan.back.cart.dto.CartItemsResponse;
import fr.volkan.back.cart.entity.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemResponse toResponse(CartItem cartItem);

    List<CartItemResponse> toCartItemResponse(List<CartItem> products);

    default CartItemsResponse toCartItemsResponse(List<CartItem> products) {
        CartItemsResponse response = new CartItemsResponse();
        response.setCartItems(toCartItemResponse(products));
        return response;
    }
}
