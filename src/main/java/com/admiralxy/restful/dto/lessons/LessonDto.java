package com.admiralxy.restful.dto.lessons;

import com.admiralxy.restful.dto.users.UserDto;
import com.admiralxy.restful.entities.DayOfWeek;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;
import java.sql.Time;

@Value
@Schema(description = "Lesson schema")
public class LessonDto implements Serializable {

    @Schema(description = "ID")
    Long id;

    @Schema(description = "Start time", type = "string", example = "11:10:00")
    Time startAt;

    @Schema(description = "End time", type = "string", example = "12:40:00")
    Time endAt;

    @Schema(description = "Day of the week")
    DayOfWeek dayOfWeek;

    @Schema(description = "Teacher user ID")
    UserDto teacher;

}
