package fr.volkan.back.product.command;

import fr.volkan.back.product.dto.ProductRequest;
import fr.volkan.back.product.dto.UpdateProductRequest;
import fr.volkan.back.product.entity.Product;
import fr.volkan.back.product.exception.ProductAlreadyExistsException;
import fr.volkan.back.product.exception.ProductNotFoundException;
import fr.volkan.back.product.repository.ProductRepository;
import fr.volkan.back.utils.ProductCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@PreAuthorize("@securityService.isAdmin()")
@Slf4j
public class ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Permet à l'administrateur de créer un produit.
     *
     * @param request Requête contenant les données pour la création du produit.
     * @return Le produit créé.
     */
    public Product createProduct(ProductRequest request) {

        if (productRepository.existsByName(request.getName())) {
            throw new ProductAlreadyExistsException();
        }

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setCreatedAt(LocalDateTime.now());

        String code;
        do {
            code = ProductCodeUtils.generateCode();
        } while (productRepository.existsByCode(code));

        product.setCode(code);

        log.info("Product created: {}", product.getName());
        return productRepository.save(product);
    }

    /**
     * Permet à l'administrateur de supprimer un produit.
     *
     * @param productId Identifiant du produit à supprimer.
     */
    public void deleteProduct(Long productId) {

        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            log.info("Product deleted: {}", productId);
        } else {
            throw new ProductNotFoundException();
        }

    }

    /**
     * Permet à l'administrateur de mettre à jour un produit.
     *
     * @param productId Identifiant du produit.
     * @param request
     * @return Le produit mis à jour.
     */
    public Product updateProduct(Long productId, UpdateProductRequest request) {

        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        if (productRepository.existsByNameAndIdIsNot(request.getName(), productId)) {
            throw new ProductAlreadyExistsException();
        }

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImage(request.getImage());
        product.setQuantity(request.getQuantity());
        product.setShellId(request.getShellId());
        product.setInventoryStatus(request.getInventoryStatus());
        product.setRating(request.getRating());
        product.setUpdatedAt(LocalDateTime.now());

        Product updatedProduct = productRepository.save(product);

        log.info("Product updated: {}", product.getName());

        return updatedProduct;
    }


}
