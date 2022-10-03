package com.admiralxy.restful.services.interfaces;

import com.admiralxy.restful.dto.tokens.BearerTokenDTO;
import com.admiralxy.restful.dto.users.UserLoginDto;

public interface IAuthService {
    BearerTokenDTO authenticate(UserLoginDto user);
}
