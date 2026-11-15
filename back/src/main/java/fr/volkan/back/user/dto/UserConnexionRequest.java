package fr.volkan.back.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConnexionRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
