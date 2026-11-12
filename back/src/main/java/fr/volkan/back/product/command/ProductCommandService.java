package fr.volkan.back.product.command;

import fr.volkan.back.product.dto.ProductRequest;
import fr.volkan.back.product.dto.UpdateProductRequest;
import fr.volkan.back.product.entity.Product;
import fr.volkan.back.product.exception.ProductAlreadyExistsException;
import fr.volkan.back.product.exception.ProductNotFoundException;
import fr.volkan.back.product.repository.ProductRepository;
import fr.volkan.back.utils.ProductCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
        } while(productRepository.existsByCode(code));

        product.setCode(code);

        log.info("Product created: {}", product.getName());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            log.info("Product deleted: {}", id);
        } else {
            throw new ProductNotFoundException();
        }

    }

    public Product updateProduct(Long id, UpdateProductRequest request) {

        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if (productRepository.existsByNameAndIdIsNot(product.getName(), id)) {
            throw new ProductAlreadyExistsException();
        }

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setUpdatedAt(LocalDateTime.now());

        Product updatedProduct = productRepository.save(product);

        log.info("Product updated: {}", product.getName());

        return updatedProduct;
    }


}
