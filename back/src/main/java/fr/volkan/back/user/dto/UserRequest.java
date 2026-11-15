package fr.volkan.back.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String firstname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
