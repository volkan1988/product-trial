package fr.volkan.back.user.repository;

import fr.volkan.back.user.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(@NotNull String username);

    boolean existsByEmail(@NotNull String email);

    Optional<User> findByUsername(@NotNull String username);
}
