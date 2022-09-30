package com.admiralxy.restful.mappers.lessons;

import com.admiralxy.restful.dto.lessons.LessonCreateDto;
import com.admiralxy.restful.dto.lessons.LessonDto;
import com.admiralxy.restful.entities.Lesson;
import com.admiralxy.restful.mappers.users.UsersMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {UsersMapper.class})
public interface LessonsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "teacher.id", source = "teacherId")
    Lesson toEntity(LessonCreateDto lessonCreateDto);

    LessonDto toDto(Lesson lesson);

}
