package com.admiralxy.restful.controllers;

import com.admiralxy.restful.dto.users.UserDto;
import com.admiralxy.restful.dto.users.UserRegisterDto;
import com.admiralxy.restful.handlers.responses.ApiError;
import com.admiralxy.restful.services.interfaces.IUsersService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UsersController {

    private IUsersService usersService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "OK")
    public Page<UserDto> getAllUsers(@RequestParam @Min(0) int page, @RequestParam @Min(1) int size) {
        return usersService.findAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public UserDto addUser(@Valid @RequestBody UserRegisterDto user) {
        return usersService.save(user);
    }

    @GetMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public UserDto getUser(@PathVariable Long userId) {
        return usersService.findById(userId);
    }

    @PutMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public UserDto updateUser(@PathVariable Long userId, @Valid @RequestBody UserRegisterDto user) {
        return usersService.update(userId, user);
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public void deleteUser(@PathVariable Long userId) {
        usersService.delete(userId);
    }
}
