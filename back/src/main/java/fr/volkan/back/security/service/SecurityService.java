package fr.volkan.back.security.service;

import fr.volkan.back.user.entity.User;
import fr.volkan.back.user.query.UserQueryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service permettant de filtrer le cas admin (utilisée uniquement dans ProductCommandService)
 */
@Service
public class SecurityService {

    public static final String ADMIN_EMAIL = "admin@admin.com";

    private final UserQueryService userQueryService;

    public SecurityService(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    public boolean isAdmin() {
        String username = Objects.requireNonNull(SecurityContextHolder.getContext()
                        .getAuthentication()).getName();

        User user = userQueryService.getUserByUsername(username);

        return ADMIN_EMAIL.equalsIgnoreCase(user.getEmail());
    }
}
