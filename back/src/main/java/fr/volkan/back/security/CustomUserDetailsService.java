package fr.volkan.back.security;

import fr.volkan.back.user.entity.User;
import fr.volkan.back.user.exception.UserNotFoundException;
import fr.volkan.back.user.query.UserQueryService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

    private final UserQueryService userQueryService;

    public CustomUserDetailsService(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        try {
            User user = userQueryService.getUserByUsername(username);

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities("ROLE_USER")
                    .build();

        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("User not found", e);
        }


    }
}
