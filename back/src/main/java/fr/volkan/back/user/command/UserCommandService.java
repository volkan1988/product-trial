package fr.volkan.back.user.command;

import fr.volkan.back.user.dto.UserRequest;
import fr.volkan.back.user.entity.User;
import fr.volkan.back.user.exception.UserAlreadyExistsException;
import fr.volkan.back.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional
public class UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCommandService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Permet la création d'un utilisateur.
     *
     * @param request La requête contenant les infos de l'utilisateur.
     * @return L'utilisateur créé.
     */
    public User createUser(UserRequest request) {

        if (userRepository.existsByUsername(request.getUsername()) || userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        log.info("User created: {} : {}", user.getId(), user.getUsername());

        return userRepository.save(user);
    }
}
