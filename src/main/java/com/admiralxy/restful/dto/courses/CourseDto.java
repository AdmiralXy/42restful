package com.admiralxy.restful.dto.courses;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class CourseDto implements Serializable {
    Long id;
    LocalDate startAt;
    LocalDate endAt;
    String name;
    String description;
}
