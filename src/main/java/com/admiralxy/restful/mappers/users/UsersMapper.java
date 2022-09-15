package com.admiralxy.restful.mappers.users;

import com.admiralxy.restful.dto.users.UserDto;
import com.admiralxy.restful.dto.users.UserRegisterDto;
import com.admiralxy.restful.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsersMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "coursesAsTeacher", ignore = true)
    @Mapping(target = "coursesAsStudent", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "role.id", source = "roleId")
    User toEntity(UserRegisterDto userDto);

    UserDto toDto(User lesson);

}
