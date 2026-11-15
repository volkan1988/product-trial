package fr.volkan.back.wish.dto;

import fr.volkan.back.product.dto.ProductResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishItemResponse {

    private ProductResponse product;
}
