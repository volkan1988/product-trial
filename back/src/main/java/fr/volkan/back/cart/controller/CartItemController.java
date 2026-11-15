package fr.volkan.back.cart.controller;

import fr.volkan.back.cart.command.CartItemCommandService;
import fr.volkan.back.cart.dto.CartItemRequest;
import fr.volkan.back.cart.dto.CartItemResponse;
import fr.volkan.back.cart.dto.CartItemsResponse;
import fr.volkan.back.cart.mapper.CartItemMapper;
import fr.volkan.back.cart.query.CartItemQueryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    private final CartItemCommandService cartItemCommandService;
    private final CartItemQueryService cartItemQueryService;
    private final CartItemMapper cartItemMapper;

    public CartItemController(CartItemCommandService cartItemCommandService, CartItemQueryService cartItemQueryService, CartItemMapper cartItemMapper) {
        this.cartItemCommandService = cartItemCommandService;
        this.cartItemQueryService = cartItemQueryService;
        this.cartItemMapper = cartItemMapper;
    }

    @GetMapping
    public CartItemsResponse getCart() {
        return cartItemMapper.toCartItemsResponse(cartItemQueryService.getCart());
    }

    @PostMapping("/{productId}")
    public CartItemResponse addProduct(@PathVariable Long productId, @Valid @RequestBody CartItemRequest request) {
        return cartItemMapper.toResponse(cartItemCommandService.addProduct(productId, request.getQuantity()));
    }

    @PutMapping("/{productId}")
    public CartItemResponse updateQuantity(@PathVariable Long productId, @Valid @RequestBody CartItemRequest request) {
        return cartItemMapper.toResponse(cartItemCommandService.updateQuantity(productId, request.getQuantity()));
    }

    @DeleteMapping("/{productId}")
    public void removeProduct(@PathVariable Long productId) {
        cartItemCommandService.removeProduct(productId);
    }

    @DeleteMapping
    public void clearCart() {
        cartItemCommandService.clearCart();
    }
}
