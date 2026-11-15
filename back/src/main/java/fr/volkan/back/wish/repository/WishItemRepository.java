package fr.volkan.back.wish.repository;

import fr.volkan.back.product.entity.Product;
import fr.volkan.back.user.entity.User;
import fr.volkan.back.wish.entity.WishItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishItemRepository extends CrudRepository<WishItem, Long> {
    Optional<WishItem> findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);

    List<WishItem> findAllByUser(User user);
}
