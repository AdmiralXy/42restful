package com.admiralxy.restful.dto.tokens;

import lombok.Value;

@Value
public class BearerTokenDTO {
    String tokenType = "Bearer";
    String token;
    Long expirationSeconds;
}
