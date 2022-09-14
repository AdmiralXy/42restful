package com.admiralxy.restful.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@NoArgsConstructor(force = true)
@Schema(description = "User create schema")
public class UserCreateDto implements Serializable {

    @Schema(description = "ID")
    Long id;

}