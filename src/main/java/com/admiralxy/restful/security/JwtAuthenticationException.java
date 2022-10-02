package com.admiralxy.restful.security;

import lombok.experimental.StandardException;
import org.springframework.security.core.AuthenticationException;

@StandardException
public class JwtAuthenticationException extends AuthenticationException {
}
