package fr.volkan.back.user.query;

import fr.volkan.back.security.jwt.JwtService;
import fr.volkan.back.user.dto.UserConnexionRequest;
import fr.volkan.back.user.dto.UserConnexionResponse;
import fr.volkan.back.user.entity.User;
import fr.volkan.back.user.exception.UserNotFoundException;
import fr.volkan.back.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserQueryService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Recherche l'utilisateur par username.
     *
     * @param username Le username servant à la recherche.
     * @return L'utilisateur trouvé (s'il existe).
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    /**
     * Permet de connecter l'utilisateur à l'application.
     *
     * @param userConnexionRequest La requête contenant les informations de l'utilisateur pour la connexion.
     * @return Un objet contenant le token d'authentification.
     */
    public UserConnexionResponse connectAccount(UserConnexionRequest userConnexionRequest) {

        User user = userRepository.findByUsername(userConnexionRequest.getUsername()).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(userConnexionRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUsername());

        UserConnexionResponse userConnexionResponse = new UserConnexionResponse();
        userConnexionResponse.setToken(token);

        return userConnexionResponse;
    }
}
