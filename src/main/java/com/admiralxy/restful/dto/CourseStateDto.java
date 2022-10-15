package com.admiralxy.restful.dto;

import com.admiralxy.restful.entities.Course;
import com.admiralxy.restful.entities.CourseState;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CourseStateDto {
    LocalDate startAt;
    LocalDate endAt;
    String name;
    String description;
    CourseState state;

    public CourseStateDto(Course course) {
        startAt = course.getStartAt();
        endAt = course.getEndAt();
        name = course.getName();
        description = course.getDescription();
        state = course.getState();
    }
}
