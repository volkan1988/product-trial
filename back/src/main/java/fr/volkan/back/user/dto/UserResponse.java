package fr.volkan.back.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String firstname;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
