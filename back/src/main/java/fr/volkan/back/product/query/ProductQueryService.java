package fr.volkan.back.product.query;

import fr.volkan.back.product.entity.Product;
import fr.volkan.back.product.exception.ProductNotFoundException;
import fr.volkan.back.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Récupère la liste des produits.
     *
     * @return La liste des produits.
     */
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    /**
     * Récupère un produit par identifiant.
     *
     * @param productId Identifiant du produit à retrouver.
     * @return Un produit par identifiant.
     */
    public Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }
}
