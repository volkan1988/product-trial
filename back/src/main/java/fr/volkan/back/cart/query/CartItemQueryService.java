package fr.volkan.back.cart.query;

import fr.volkan.back.cart.entity.CartItem;
import fr.volkan.back.cart.repository.CartItemRepository;
import fr.volkan.back.user.entity.User;
import fr.volkan.back.user.query.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@Slf4j
public class CartItemQueryService {

    private final CartItemRepository cartItemRepository;
    private final UserQueryService userQueryService;

    public CartItemQueryService(CartItemRepository cartItemRepository, UserQueryService userQueryService) {
        this.cartItemRepository = cartItemRepository;
        this.userQueryService = userQueryService;
    }

    /**
     * Retourne la liste des éléments du panier de l'utilisateur du contexte.
     *
     * @return La liste des éléments du panier de l'utilisateur du contexte.
     */
    public List<CartItem> getCart() {
        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        User user = userQueryService.getUserByUsername(username);

        return cartItemRepository.findAllByUser(user);
    }
}
