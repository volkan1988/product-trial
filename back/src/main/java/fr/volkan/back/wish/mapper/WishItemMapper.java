package fr.volkan.back.wish.mapper;

import fr.volkan.back.wish.dto.WishItemResponse;
import fr.volkan.back.wish.dto.WishItemsResponse;
import fr.volkan.back.wish.entity.WishItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WishItemMapper {

    WishItemResponse toResponse(WishItem wishItem);

    List<WishItemResponse> toWishItemResponse(List<WishItem> products);

    default WishItemsResponse toWishItemsResponse(List<WishItem> products) {
        WishItemsResponse response = new WishItemsResponse();
        response.setWishItems(toWishItemResponse(products));
        return response;
    }
}
