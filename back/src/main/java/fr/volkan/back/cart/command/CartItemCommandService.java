package fr.volkan.back.cart.command;

import fr.volkan.back.cart.entity.CartItem;
import fr.volkan.back.cart.repository.CartItemRepository;
import fr.volkan.back.product.entity.Product;
import fr.volkan.back.product.query.ProductQueryService;
import fr.volkan.back.user.entity.User;
import fr.volkan.back.user.query.UserQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@Slf4j
public class CartItemCommandService {

    private final CartItemRepository cartItemRepository;
    private final UserQueryService userQueryService;
    private final ProductQueryService productQueryService;


    public CartItemCommandService(CartItemRepository cartItemRepository, UserQueryService userQueryService, ProductQueryService productQueryService) {
        this.cartItemRepository = cartItemRepository;
        this.userQueryService = userQueryService;
        this.productQueryService = productQueryService;
    }

    /**
     * Ajoute x quantité de produits au panier de l'utilisateur du contexte.
     *
     * @param productId L'identifiant du produit.
     * @param quantity La quantité de produits à ajouter.
     * @return L'élément du panier ajouté.
     */
    public CartItem addProduct(Long productId, int quantity) {

        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        User user = userQueryService.getUserByUsername(username);
        Product product = productQueryService.findProduct(productId);

        CartItem cartItem = cartItemRepository
                .findByUserAndProduct(user, product)
                .orElseGet(() -> {
                    CartItem newCartItem = new CartItem();
                    newCartItem.setUser(user);
                    newCartItem.setProduct(product);
                    newCartItem.setQuantity(0);
                    return newCartItem;
                });

        cartItem.setQuantity(cartItem.getQuantity() + quantity);

        log.info("Adding product {} to cart", product.getId());
        return cartItemRepository.save(cartItem);
    }

    /**
     * Met à jour la quantité du produit dans le panier.
     *
     * @param productId L'identifiant du produit.
     * @param quantity La quantité à mettre à jour.
     * @return L'élément du panier modifié.
     */
    public CartItem updateQuantity(Long productId, int quantity) {

        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        User user = userQueryService.getUserByUsername(username);
        Product product = productQueryService.findProduct(productId);

        CartItem cartItem = cartItemRepository
                .findByUserAndProduct(user, product)
                .orElseThrow(() -> new EntityNotFoundException("Product not found in cart"));

        cartItem.setQuantity(quantity);

        log.info("Updating product {} to cart", product.getId());
        return cartItemRepository.save(cartItem);
    }

    /**
     * Supprime un produit du panier.
     *
     * @param productId L'identifiant du produit à supprimer.
     */
    public void removeProduct(Long productId) {

        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        User user = userQueryService.getUserByUsername(username);
        Product product = productQueryService.findProduct(productId);

        log.info("Removing product {} from cart", product.getId());
        cartItemRepository.deleteByUserAndProduct(user, product);
    }

    /**
     * Vide le panier.
     */
    public void clearCart() {

        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        User user = userQueryService.getUserByUsername(username);

        log.info("Clearing cart");
        cartItemRepository.deleteAllByUser(user);
    }
}
