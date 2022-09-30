package com.admiralxy.restful.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

@Value
@Schema(description = "User register schema")
public class UserRegisterDto implements Serializable {

    @Schema(description = "First name")
    String firstName;

    @Schema(description = "Last name")
    String lastName;

    @Schema(description = "Login")
    String login;

    @Schema(description = "Password")
    String password;

    @Schema(description = "Role ID", allowableValues = {"1", "2", "3"})
    Long roleId;

}