package fr.volkan.back.cart.repository;

import fr.volkan.back.cart.entity.CartItem;
import fr.volkan.back.product.entity.Product;
import fr.volkan.back.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    Optional<CartItem> findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);

    void deleteAllByUser(User user);

    List<CartItem> findAllByUser(User user);
}
