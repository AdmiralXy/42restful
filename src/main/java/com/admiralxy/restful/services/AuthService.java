package com.admiralxy.restful.services;

import com.admiralxy.restful.dto.tokens.BearerTokenDTO;
import com.admiralxy.restful.dto.users.UserLoginDto;
import com.admiralxy.restful.entities.User;
import com.admiralxy.restful.repositories.UsersRepository;
import com.admiralxy.restful.security.JwtTokenProvider;
import com.admiralxy.restful.services.interfaces.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private final UsersRepository usersRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Override
    public BearerTokenDTO authenticate(UserLoginDto userDto) {
        User user = usersRepository.findByLogin(userDto.getLogin()).orElseThrow(() ->
                new BadCredentialsException("User not found"));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid password");

        String token = jwtTokenProvider.create(user.getId(), user.getLogin(), user.getRole().getName());
        return new BearerTokenDTO(token, jwtTokenProvider.getExpirationSeconds());
    }
}
