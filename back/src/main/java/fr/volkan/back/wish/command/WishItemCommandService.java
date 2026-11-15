package fr.volkan.back.wish.command;

import fr.volkan.back.product.entity.Product;
import fr.volkan.back.product.query.ProductQueryService;
import fr.volkan.back.user.entity.User;
import fr.volkan.back.user.query.UserQueryService;
import fr.volkan.back.wish.entity.WishItem;
import fr.volkan.back.wish.repository.WishItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@Slf4j
public class WishItemCommandService {

    private final WishItemRepository wishItemRepository;
    private final UserQueryService userQueryService;
    private final ProductQueryService productQueryService;

    public WishItemCommandService(WishItemRepository wishItemRepository, UserQueryService userQueryService, ProductQueryService productQueryService) {
        this.wishItemRepository = wishItemRepository;
        this.userQueryService = userQueryService;
        this.productQueryService = productQueryService;
    }

    /**
     * Permet d'ajouter un produit à la liste de souhaits de l'utilisateur du contexte.
     *
     * @param productId L'identifiant du produit.
     * @return Le produit de la liste de souhait ajouté.
     */
    public WishItem addProduct(Long productId) {
        String username = Objects.requireNonNull(
                SecurityContextHolder.getContext().getAuthentication()
        ).getName();

        User user = userQueryService.getUserByUsername(username);
        Product product = productQueryService.findProduct(productId);

        return wishItemRepository
                .findByUserAndProduct(user, product)
                .orElseGet(() -> {
                    WishItem newItem = new WishItem();
                    newItem.setUser(user);
                    newItem.setProduct(product);
                    return wishItemRepository.save(newItem);
                });
    }

    /**
     * Supprime un produit de la liste d'envie de l'utilisateur du contexte.
     *
     * @param productId Identifiant du produit.
     */
    public void removeProduct(Long productId) {
        String username = Objects.requireNonNull(
                SecurityContextHolder.getContext().getAuthentication()
        ).getName();

        User user = userQueryService.getUserByUsername(username);
        Product product = productQueryService.findProduct(productId);

        wishItemRepository.deleteByUserAndProduct(user, product);
    }

}
