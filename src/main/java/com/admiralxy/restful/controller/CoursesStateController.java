package com.admiralxy.restful.controller;

import com.admiralxy.restful.dto.CourseStateDto;
import com.admiralxy.restful.entities.Course;
import com.admiralxy.restful.entities.CourseState;
import com.admiralxy.restful.exceptions.NotFoundException;
import com.admiralxy.restful.repositories.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@BasePathAwareController
@RequiredArgsConstructor
public class CoursesStateController {

    private final CoursesRepository coursesRepository;

    @PostMapping("courses/{id}/publish")
    public ResponseEntity<?> publish(@PathVariable Long id) {
        Course course = coursesRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));
        course.setState(CourseState.Published);
        coursesRepository.save(course);
        return ResponseEntity.ok(new CourseStateDto(course));
    }
}
