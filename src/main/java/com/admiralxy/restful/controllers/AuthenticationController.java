package com.admiralxy.restful.controllers;

import com.admiralxy.restful.dto.tokens.BearerTokenDTO;
import com.admiralxy.restful.dto.users.UserLoginDto;
import com.admiralxy.restful.services.interfaces.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final IAuthService authService;

    @PostMapping("signUp")
    public BearerTokenDTO login(@Valid @RequestBody UserLoginDto userDTO) {
        return authService.authenticate(userDTO);
    }
}
