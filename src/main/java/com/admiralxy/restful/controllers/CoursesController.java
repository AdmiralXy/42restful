package com.admiralxy.restful.controllers;

import com.admiralxy.restful.dto.courses.CourseCreateDto;
import com.admiralxy.restful.dto.courses.CourseDto;
import com.admiralxy.restful.dto.lessons.LessonCreateDto;
import com.admiralxy.restful.dto.lessons.LessonDto;
import com.admiralxy.restful.dto.users.UserCreateDto;
import com.admiralxy.restful.dto.users.UserDto;
import com.admiralxy.restful.handlers.responses.ApiError;
import com.admiralxy.restful.services.interfaces.ICoursesService;
import com.admiralxy.restful.services.interfaces.ILessonsService;
import com.admiralxy.restful.services.interfaces.IStudentsService;
import com.admiralxy.restful.services.interfaces.ITeachersService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("courses")
@AllArgsConstructor
public class CoursesController {

    private final ICoursesService coursesService;

    private final ILessonsService lessonsService;

    private final IStudentsService studentsService;

    private final ITeachersService teachersService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "OK")
    public Page<CourseDto> getAllCourses(@RequestParam @Min(0) int page, @RequestParam @Min(1) int size) {
        return coursesService.findAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public CourseDto addCourse(@Valid @RequestBody CourseCreateDto course) {
        return coursesService.save(course);
    }

    @GetMapping("{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public CourseDto getCourse(@PathVariable Long courseId) {
        return coursesService.findById(courseId);
    }

    @PutMapping("{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public CourseDto updateCourse(@PathVariable Long courseId, @Valid @RequestBody CourseCreateDto course) {
        return coursesService.update(courseId, course);
    }

    @DeleteMapping("{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public void deleteCourse(@PathVariable Long courseId) {
        coursesService.delete(courseId);
    }

    @GetMapping("{courseId}/lessons")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public Page<LessonDto> getLessonsByCourse(@PathVariable Long courseId, @RequestParam @Min(0) int page, @RequestParam @Min(1) int size) {
        return lessonsService.findByCourseId(courseId, page, size);
    }

    @PostMapping("{courseId}/lessons")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public LessonDto addLessonToCourse(@PathVariable Long courseId, @Valid @RequestBody LessonCreateDto lesson) {
        return lessonsService.saveByCourseId(courseId, lesson);
    }

    @PutMapping("{courseId}/lessons/{lessonId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public LessonDto updateLessonInCourse(@PathVariable Long courseId, @PathVariable Long lessonId, @Valid @RequestBody LessonCreateDto lesson) {
        return lessonsService.updateByCourseId(courseId, lessonId, lesson);
    }

    @DeleteMapping("{courseId}/lessons/{lessonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public void deleteLessonFromCourse(@PathVariable Long courseId, @PathVariable Long lessonId) {
        lessonsService.deleteByCourseId(courseId, lessonId);
    }

    @GetMapping("{courseId}/students")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public Page<UserDto> getStudentsByCourse(@PathVariable Long courseId, @RequestParam @Min(0) int page, @RequestParam @Min(1) int size) {
        return studentsService.findByCourseId(courseId, page, size);
    }

    @PostMapping("{courseId}/students")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public UserDto addStudentToCourse(@PathVariable Long courseId, @Valid @RequestBody UserCreateDto user) {
        return studentsService.saveByCourseId(courseId, user);
    }

    @DeleteMapping("{courseId}/students/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public void deleteStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        studentsService.deleteByCourseId(courseId, studentId);
    }

    @GetMapping("{courseId}/teachers")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public Page<UserDto> getTeachersByCourse(@PathVariable Long courseId, @RequestParam @Min(0) int page, @RequestParam @Min(1) int size) {
        return teachersService.findByCourseId(courseId, page, size);
    }

    @PostMapping("{courseId}/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public UserDto addTeacherToCourse(@PathVariable Long courseId, @Valid @RequestBody UserCreateDto user) {
        return teachersService.saveByCourseId(courseId, user);
    }

    @DeleteMapping("{courseId}/teachers/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public void deleteTeacherFromCourse(@PathVariable Long courseId, @PathVariable Long teacherId) {
        teachersService.deleteByCourseId(courseId, teacherId);
    }
}
