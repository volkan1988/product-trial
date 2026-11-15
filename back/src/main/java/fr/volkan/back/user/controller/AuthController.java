package fr.volkan.back.user.controller;

import fr.volkan.back.user.command.UserCommandService;
import fr.volkan.back.user.dto.UserConnexionRequest;
import fr.volkan.back.user.dto.UserConnexionResponse;
import fr.volkan.back.user.dto.UserRequest;
import fr.volkan.back.user.dto.UserResponse;
import fr.volkan.back.user.mapper.UserMapper;
import fr.volkan.back.user.query.UserQueryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final UserMapper userMapper;

    public AuthController(UserCommandService userCommandService, UserQueryService userQueryService, UserMapper userMapper) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createAccount(@Valid @RequestBody UserRequest userRequest) {
        return userMapper.toResponse(userCommandService.createUser(userRequest));
    }

    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserConnexionResponse connectAccount(@Valid @RequestBody UserConnexionRequest userConnexionRequest) {
        return userQueryService.connectAccount(userConnexionRequest);
    }

}
