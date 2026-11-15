package fr.volkan.back.wish.command;

import fr.volkan.back.user.entity.User;
import fr.volkan.back.user.query.UserQueryService;
import fr.volkan.back.wish.entity.WishItem;
import fr.volkan.back.wish.repository.WishItemRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class WishItemQueryService {

    private final WishItemRepository wishItemRepository;
    private final UserQueryService userQueryService;

    public WishItemQueryService(WishItemRepository wishItemRepository, UserQueryService userQueryService) {
        this.wishItemRepository = wishItemRepository;
        this.userQueryService = userQueryService;
    }

    /**
     * Retourne la liste de souhaits de l'utilisateur du contexte.
     *
     * @return La liste de souhaits de l'utilisateur du contexte.
     */
    public List<WishItem> getWishList() {

        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        User user = userQueryService.getUserByUsername(username);

        return wishItemRepository.findAllByUser(user);
    }
}
