package fr.volkan.back.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConnexionRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
