package com.admiralxy.restful.services.interfaces;

import com.admiralxy.restful.dto.users.UserRegisterDto;
import com.admiralxy.restful.dto.users.UserDto;
import org.springframework.data.domain.Page;

public interface IUsersService {
    Page<UserDto> findAll(int page, int size);
    UserDto save(UserRegisterDto user);
    UserDto findById(Long id);
    UserDto update(Long id, UserRegisterDto user);
    void delete(Long id);
    UserDto findByLogin(String login);
}
