package com.admiralxy.restful.security;

import lombok.Value;

@Value
public class JwtUser {
    Long id;
    String login;
    String role;
}
