package com.admiralxy.restful.dto.courses;

import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Value
public class CourseCreateDto implements Serializable {

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startAt;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endAt;

    @NotNull
    @Length(min = 3, max = 255)
    String name;

    @NotNull
    @Length(min = 3, max = 255)
    String description;

}
