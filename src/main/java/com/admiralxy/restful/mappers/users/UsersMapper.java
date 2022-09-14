package com.admiralxy.restful.mappers.users;

import com.admiralxy.restful.dto.users.UserCreateDto;
import com.admiralxy.restful.dto.users.UserDto;
import com.admiralxy.restful.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsersMapper {

    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "login", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "coursesAsStudent", ignore = true)
    @Mapping(target = "coursesAsTeacher", ignore = true)
    User toEntity(UserCreateDto userDto);

    UserDto toDto(User lesson);

}
