package fr.volkan.back.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductsResponse {

    private List<ProductResponse> products;
}
