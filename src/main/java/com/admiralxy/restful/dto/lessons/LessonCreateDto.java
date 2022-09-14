package com.admiralxy.restful.dto.lessons;

import com.admiralxy.restful.entities.DayOfWeek;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

@Value
@Schema(description = "Lesson create schema")
public class LessonCreateDto implements Serializable {

    @NotNull
    @Schema(description = "Start time", type = "string", example = "11:10:00")
    Time startAt;

    @NotNull
    @Schema(description = "End time", type = "string", example = "12:40:00")
    Time endAt;

    @NotNull
    @Schema(description = "Day of the week")
    DayOfWeek dayOfWeek;

    @NotNull
    @Schema(description = "Teacher user ID")
    Long teacherId;

}
