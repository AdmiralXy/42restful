package com.admiralxy.restful.dto.users;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.admiralxy.restful.entities.User} entity
 */
@Data
public class UserLoginDto implements Serializable {
    private final String login;
    private final String password;
}