package com.admiralxy.restful.services.interfaces;

import com.admiralxy.restful.dto.users.UserCreateDto;
import com.admiralxy.restful.dto.users.UserDto;
import org.springframework.data.domain.Page;

public interface ITeachersService {
    Page<UserDto> findByCourseId(Long courseId, int page, int size);
    UserDto saveByCourseId(Long courseId, UserCreateDto user);
    void deleteByCourseId(Long courseId, Long studentId);
}
