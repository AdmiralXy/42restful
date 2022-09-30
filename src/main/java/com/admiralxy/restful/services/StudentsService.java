package com.admiralxy.restful.services;

import com.admiralxy.restful.dto.users.UserCreateDto;
import com.admiralxy.restful.dto.users.UserDto;
import com.admiralxy.restful.entities.Course;
import com.admiralxy.restful.entities.User;
import com.admiralxy.restful.exceptions.BadRequestException;
import com.admiralxy.restful.exceptions.NotFoundException;
import com.admiralxy.restful.mappers.users.UsersMapper;
import com.admiralxy.restful.repositories.CoursesRepository;
import com.admiralxy.restful.repositories.UsersRepository;
import com.admiralxy.restful.services.interfaces.IStudentsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentsService implements IStudentsService {

    private final CoursesRepository coursesRepository;

    private final UsersRepository usersRepository;

    private final UsersMapper mapper;

    @Override
    public Page<UserDto> findByCourseId(Long courseId, int page, int size) {
        Optional<Course> course = coursesRepository.findById(courseId);
        if (!course.isPresent())
            throw new NotFoundException("Course not found");
        return usersRepository.findByCoursesAsStudentContains(course.get(), PageRequest.of(page, size))
                .map(mapper::toDto);
    }

    @Override
    public UserDto saveByCourseId(Long courseId, UserCreateDto user) {
        Course course = coursesRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
        User toInsert = usersRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User not found"));
        if (course.getStudents().contains(toInsert))
            throw new BadRequestException("User already enrolled in this course");
        course.getStudents().add(toInsert);
        coursesRepository.save(course);
        return mapper.toDto(toInsert);
    }

    @Override
    public void deleteByCourseId(Long courseId, Long studentId) {
        Course course = coursesRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
        User toDelete = usersRepository.findById(studentId).orElseThrow(() -> new NotFoundException("User not found"));
        if (!course.getStudents().contains(toDelete))
            throw new BadRequestException("User not enrolled in this course");
        course.getStudents().remove(toDelete);
        coursesRepository.save(course);
    }
}
