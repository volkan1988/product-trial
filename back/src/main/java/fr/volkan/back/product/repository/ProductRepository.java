package fr.volkan.back.product.repository;

import fr.volkan.back.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdIsNot(String name, Long id);

    boolean existsByCode(String code);
}
