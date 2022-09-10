package com.admiralxy.restful.services.interfaces;

import com.admiralxy.restful.dto.courses.CourseCreateDto;
import com.admiralxy.restful.dto.courses.CourseDto;
import org.springframework.data.domain.Page;

public interface ICoursesService {
    Page<CourseDto> findAll(int page, int size);
    CourseDto save(CourseCreateDto course);
    CourseDto findById(Long id);
    CourseDto update(Long id, CourseCreateDto course);
    void delete(Long id);
}
