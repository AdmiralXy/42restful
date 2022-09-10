package com.admiralxy.restful.controllers;

import com.admiralxy.restful.dto.courses.CourseCreateDto;
import com.admiralxy.restful.dto.courses.CourseDto;
import com.admiralxy.restful.handlers.responses.ApiError;
import com.admiralxy.restful.services.interfaces.ICoursesService;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "OK")
    public Page<CourseDto> getAllCourses(@RequestParam @Min(0) int page, @RequestParam @Min(1) int size) {
        return coursesService.findAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public CourseDto addCourse(@Valid @RequestBody CourseCreateDto course) {
        return coursesService.save(course);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found"),
            @ApiResponse(responseCode = "404", description = "Course not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public CourseDto getCourse(@PathVariable("id") Long id) {
        return coursesService.findById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            }),
            @ApiResponse(responseCode = "404", description = "Course not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public CourseDto updateCourse(@PathVariable("id") Long id, @Valid @RequestBody CourseCreateDto course) {
        return coursesService.update(id, course);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted"),
            @ApiResponse(responseCode = "404", description = "Course not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
            })
    })
    public void deleteCourse(@PathVariable("id") Long id) {
        coursesService.delete(id);
    }
}
