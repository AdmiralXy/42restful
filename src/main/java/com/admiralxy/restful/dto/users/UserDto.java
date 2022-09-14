package com.admiralxy.restful.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;

@Value
@Schema(description = "User schema")
public class UserDto implements Serializable {

    @Schema(description = "ID")
    Long id;

    @Schema(description = "First name")
    String firstName;

    @Schema(description = "Last name")
    String lastName;

}