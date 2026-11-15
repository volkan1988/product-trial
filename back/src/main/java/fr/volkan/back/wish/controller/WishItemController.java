package fr.volkan.back.wish.controller;

import fr.volkan.back.wish.command.WishItemCommandService;
import fr.volkan.back.wish.query.WishItemQueryService;
import fr.volkan.back.wish.dto.WishItemResponse;
import fr.volkan.back.wish.dto.WishItemsResponse;
import fr.volkan.back.wish.mapper.WishItemMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wish")
public class WishItemController {

    private final WishItemCommandService wishItemCommandService;
    private final WishItemQueryService wishItemQueryService;
    private final WishItemMapper wishItemMapper;

    public WishItemController(WishItemCommandService wishItemCommandService, WishItemQueryService wishItemQueryService, WishItemMapper wishItemMapper) {
        this.wishItemCommandService = wishItemCommandService;
        this.wishItemQueryService = wishItemQueryService;
        this.wishItemMapper = wishItemMapper;
    }

    @GetMapping
    public WishItemsResponse getWishList() {
        return wishItemMapper.toWishItemsResponse(wishItemQueryService.getWishList());
    }

    @PostMapping("/{productId}")
    public WishItemResponse addProduct(@PathVariable Long productId) {
        return wishItemMapper.toResponse(wishItemCommandService.addProduct(productId));
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProduct(@PathVariable Long productId) {
        wishItemCommandService.removeProduct(productId);
    }
}
