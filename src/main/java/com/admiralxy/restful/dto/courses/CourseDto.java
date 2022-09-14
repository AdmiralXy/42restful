package com.admiralxy.restful.dto.courses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@Schema(description = "Course schema")
public class CourseDto implements Serializable {

    @Schema(description = "ID")
    Long id;

    @Schema(description = "Start date")
    LocalDate startAt;

    @Schema(description = "End date")
    LocalDate endAt;

    @Schema(description = "Name")
    String name;

    @Schema(description = "Description")
    String description;

}
