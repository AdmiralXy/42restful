package com.admiralxy.restful.services;

import com.admiralxy.restful.dto.lessons.LessonCreateDto;
import com.admiralxy.restful.dto.lessons.LessonDto;
import com.admiralxy.restful.entities.Course;
import com.admiralxy.restful.entities.Lesson;
import com.admiralxy.restful.exceptions.NotFoundException;
import com.admiralxy.restful.mappers.lessons.LessonsMapper;
import com.admiralxy.restful.repositories.CoursesRepository;
import com.admiralxy.restful.repositories.LessonsRepository;
import com.admiralxy.restful.services.interfaces.ILessonsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonsService implements ILessonsService {

    private final CoursesRepository coursesRepository;

    private final LessonsRepository lessonsRepository;

    private final LessonsMapper mapper;

    @Override
    public Page<LessonDto> findByCourseId(Long courseId, int page, int size) {
        if (!coursesRepository.existsById(courseId))
            throw new NotFoundException("Course not found");
        return lessonsRepository.findByCourseId(courseId, PageRequest.of(page, size)).map(mapper::toDto);
    }

    @Override
    public LessonDto saveByCourseId(Long courseId, LessonCreateDto lesson) {
        Course course = coursesRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
        Lesson toCreate = mapper.toEntity(lesson);
        toCreate.setCourse(course);
        return mapper.toDto(lessonsRepository.save(toCreate));
    }

    @Override
    public LessonDto updateByCourseId(Long courseId, Long lessonId, LessonCreateDto lesson) {
        Course course = coursesRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
        if (!lessonsRepository.existsById(lessonId))
            throw new NotFoundException("Lesson not found");
        Lesson toSave = mapper.toEntity(lesson);
        toSave.setId(lessonId);
        toSave.setCourse(course);
        return mapper.toDto(lessonsRepository.save(toSave));
    }

    @Override
    public void deleteByCourseId(Long courseId, Long lessonId) {
        Lesson lesson = lessonsRepository.findByIdAndCourseId(lessonId, courseId)
                .orElseThrow(() -> new NotFoundException("Lesson not found"));
        lessonsRepository.delete(lesson);
    }
}
