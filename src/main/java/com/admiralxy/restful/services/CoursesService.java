package com.admiralxy.restful.services;

import com.admiralxy.restful.dto.courses.CourseCreateDto;
import com.admiralxy.restful.dto.courses.CourseDto;
import com.admiralxy.restful.entities.Course;
import com.admiralxy.restful.exceptions.NotFoundException;
import com.admiralxy.restful.mappers.courses.CoursesMapper;
import com.admiralxy.restful.repositories.CoursesRepository;
import com.admiralxy.restful.services.interfaces.ICoursesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoursesService implements ICoursesService {

    private final CoursesRepository repository;

    private final CoursesMapper mapper;


    public Page<CourseDto> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).map(mapper::toDto);
    }

    public CourseDto save(CourseCreateDto course) {
        Course toCreate = mapper.toEntity(course);
        return mapper.toDto(repository.save(toCreate));
    }

    public CourseDto findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() ->
                new NotFoundException("Course not found")
        );
    }

    public CourseDto update(Long id, CourseCreateDto course) {
        if (!repository.existsById(id))
            throw new NotFoundException("Course not found");
        Course toUpdate = mapper.toEntity(course);
        toUpdate.setId(id);
        return mapper.toDto(repository.save(toUpdate));
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundException("Course not found");
        repository.deleteById(id);
    }
}
