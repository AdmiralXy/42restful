package com.admiralxy.restful.services;

import com.admiralxy.restful.dto.tokens.BearerTokenDTO;
import com.admiralxy.restful.dto.users.UserLoginDto;
import com.admiralxy.restful.entities.User;
import com.admiralxy.restful.repositories.UsersRepository;
import com.admiralxy.restful.security.JwtTokenProvider;
import com.admiralxy.restful.services.interfaces.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UsersRepository usersRepository;

    @Override
    public BearerTokenDTO authenticate(UserLoginDto user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
            User userFromDb = usersRepository.findByLogin(user.getLogin()).orElseThrow(() ->
                    new BadCredentialsException("User not found")
            );
            String token = jwtTokenProvider.createToken(userFromDb.getId(), userFromDb.getLogin(), Collections.singletonList(userFromDb.getRole()));
            return new BearerTokenDTO(token, jwtTokenProvider.getExpirationSeconds());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
