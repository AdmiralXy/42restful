package com.admiralxy.restful.mappers.courses;

import com.admiralxy.restful.dto.courses.CourseCreateDto;
import com.admiralxy.restful.dto.courses.CourseDto;
import com.admiralxy.restful.entities.Course;
import org.mapstruct.*;

@Mapper
public interface CoursesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    Course toEntity(CourseCreateDto courseCreateDto);

    CourseDto toDto(Course course);

}
