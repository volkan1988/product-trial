package fr.volkan.back.product.mapper;

import fr.volkan.back.product.dto.ProductResponse;
import fr.volkan.back.product.dto.ProductsResponse;
import fr.volkan.back.product.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    List<ProductResponse> toProductResponse(List<Product> products);

    default ProductsResponse toProductsResponse(List<Product> products) {
        ProductsResponse response = new ProductsResponse();
        response.setProducts(toProductResponse(products));
        return response;
    }
}
