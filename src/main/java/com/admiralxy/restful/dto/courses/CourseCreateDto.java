package com.admiralxy.restful.dto.courses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Value
@Schema(description = "Course create schema")
public class CourseCreateDto implements Serializable {

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "Start date")
    LocalDate startAt;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "End date")
    LocalDate endAt;

    @NotNull
    @Length(min = 3, max = 255)
    @Schema(description = "Name")
    String name;

    @NotNull
    @Length(min = 3, max = 255)
    @Schema(description = "Description")
    String description;

}
