package com.admiralxy.restful.services.interfaces;

import com.admiralxy.restful.dto.lessons.LessonCreateDto;
import com.admiralxy.restful.dto.lessons.LessonDto;
import org.springframework.data.domain.Page;

public interface ILessonsService {
    Page<LessonDto> findByCourseId(Long courseId, int page, int size);
    LessonDto saveByCourseId(Long courseId, LessonCreateDto lesson);
    LessonDto updateByCourseId(Long courseId, Long lessonId, LessonCreateDto lesson);
    void deleteByCourseId(Long courseId, Long lessonId);
}
