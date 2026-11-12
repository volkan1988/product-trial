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

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}
