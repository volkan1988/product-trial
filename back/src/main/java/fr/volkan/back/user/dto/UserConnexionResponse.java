package fr.volkan.back.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConnexionResponse {

    @NotNull
    private String token;
}
