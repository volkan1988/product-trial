package fr.volkan.back.product.controller;

import fr.volkan.back.product.command.ProductCommandService;
import fr.volkan.back.product.dto.ProductRequest;
import fr.volkan.back.product.dto.ProductResponse;
import fr.volkan.back.product.dto.ProductsResponse;
import fr.volkan.back.product.dto.UpdateProductRequest;
import fr.volkan.back.product.mapper.ProductMapper;
import fr.volkan.back.product.query.ProductQueryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;
    private final ProductMapper productMapper;

    public ProductController(ProductQueryService productQueryService, ProductCommandService productCommandService, ProductMapper productMapper) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
        this.productMapper = productMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductsResponse getProducts() {
        return productMapper.toProductsResponse(productQueryService.findProducts());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getProductById(@PathVariable Long id) {
        return productMapper.toResponse(productQueryService.findProduct(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productMapper.toResponse(productCommandService.createProduct(productRequest));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest request) {
        return productMapper.toResponse(productCommandService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productCommandService.deleteProduct(id);
    }

}
