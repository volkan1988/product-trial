package fr.volkan.back.wish.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WishItemsResponse {

    List<WishItemResponse> wishItems;
}
