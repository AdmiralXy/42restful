package com.admiralxy.restful;

import com.admiralxy.restful.dto.courses.CourseCreateDto;
import com.admiralxy.restful.dto.courses.CourseDto;
import com.admiralxy.restful.exceptions.NotFoundException;
import com.admiralxy.restful.services.interfaces.ICoursesService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CoursesTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICoursesService coursesService;

    @BeforeEach
    public void setUp() {
        Page<CourseDto> courseDtoPage = new PageImpl<>(Lists.list(
                new CourseDto(
                        1L,
                        LocalDate.of(2022, 5, 11),
                        LocalDate.of(2023, 3, 23),
                        "SQL Basics",
                        "Course for beginners"
                ),
                new CourseDto(
                        2L,
                        LocalDate.of(2023, 2, 5),
                        LocalDate.of(2024, 2, 13),
                        "SQL Advanced",
                        "Course for advanced users"
                ),
                new CourseDto(
                        3L,
                        LocalDate.of(2025, 8, 6),
                        LocalDate.of(2026, 12, 7),
                        "SQL Expert",
                        "Course for experts"
                ),
                new CourseDto(
                        4L,
                        LocalDate.of(2026, 7, 14),
                        LocalDate.of(2027, 3, 14),
                        "SQL Master",
                        "Course for masters"
                ),
                new CourseDto(
                        5L,
                        LocalDate.of(2026, 1, 16),
                        LocalDate.of(2028, 5, 1),
                        "Hibernate Basics",
                        "Course for beginners"
                )
        ));

        when(coursesService.findAll(0, 5)).thenReturn(courseDtoPage);
        when(coursesService.findAll(1, 5)).thenReturn(Page.empty());

        when(coursesService.save(new CourseCreateDto(
                LocalDate.of(2022, 5, 11),
                LocalDate.of(2023, 3, 23),
                "SQL Basics",
                "Course for beginners"
        ))).thenReturn(new CourseDto(
                1L,
                LocalDate.of(2022, 5, 11),
                LocalDate.of(2023, 3, 23),
                "SQL Basics",
                "Course for beginners"
        ));

        when(coursesService.findById(1L)).thenReturn(new CourseDto(
                1L,
                LocalDate.of(2022, 5, 11),
                LocalDate.of(2023, 3, 23),
                "SQL Basics",
                "Course for beginners"
        ));

        when(coursesService.findById(2L)).thenThrow(NotFoundException.class);

        when(coursesService.update(1L, new CourseCreateDto(
                LocalDate.of(2022, 5, 11),
                LocalDate.of(2023, 3, 23),
                "SQL Basics",
                "Course for beginners"
        ))).thenReturn(new CourseDto(
                1L,
                LocalDate.of(2022, 5, 11),
                LocalDate.of(2023, 3, 23),
                "SQL Basics",
                "Course for beginners"
        ));

        when(coursesService.update(2L, new CourseCreateDto(
                LocalDate.of(2022, 5, 11),
                LocalDate.of(2023, 3, 23),
                "SQL Basics",
                "Course for beginners"
        ))).thenThrow(NotFoundException.class);

        doNothing().when(coursesService).delete(1L);
        doThrow(NotFoundException.class).when(coursesService).delete(2L);
    }

    @Test
    @WithMockUser(username = "user", roles={"ADMIN"})
    public void getAllCoursesTest() throws Exception {
        mockMvc.perform(get("/courses?page=0&size=5")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.totalElements").value(5))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].startAt").value("2022-05-11"))
                .andExpect(jsonPath("$.content[4].name").value("Hibernate Basics"));

        mockMvc.perform(get("/courses?page=1&size=5")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empty").value(true))
                .andExpect(jsonPath("$.content").isEmpty());
    }

    @Test
    @WithMockUser(username = "user", roles={"ADMIN"})
    public void addCourseTest() throws Exception {
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\n" +
                                "  \"startAt\": \"2022-05-11\",\n" +
                                "  \"endAt\": \"2023-03-23\",\n" +
                                "  \"name\": \"SQL Basics\",\n" +
                                "  \"description\": \"Course for beginners\"\n" +
                                "}"
                        )
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.startAt").value("2022-05-11"))
                .andExpect(jsonPath("$.endAt").value("2023-03-23"))
                .andExpect(jsonPath("$.name").value("SQL Basics"))
                .andExpect(jsonPath("$.description").value("Course for beginners"));

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\n" +
                                "  \"endAt\": \"20100-03-49\",\n" +
                                "  \"name\": \"SQL Basics\",\n" +
                                "  \"description\": \"Course for beginners\"\n" +
                                "}"
                        )
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user", roles={"ADMIN"})
    public void getCourseTest() throws Exception {
        mockMvc.perform(get("/courses/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.startAt").value("2022-05-11"))
                .andExpect(jsonPath("$.endAt").value("2023-03-23"))
                .andExpect(jsonPath("$.name").value("SQL Basics"))
                .andExpect(jsonPath("$.description").value("Course for beginners"));

        mockMvc.perform(get("/courses/2")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles={"ADMIN"})
    public void updateCourseTest() throws Exception {
        mockMvc.perform(put("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\n" +
                                "  \"startAt\": \"2022-05-11\",\n" +
                                "  \"endAt\": \"2023-03-23\",\n" +
                                "  \"name\": \"SQL Basics\",\n" +
                                "  \"description\": \"Course for beginners\"\n" +
                                "}"
                        )
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.startAt").value("2022-05-11"))
                .andExpect(jsonPath("$.endAt").value("2023-03-23"))
                .andExpect(jsonPath("$.name").value("SQL Basics"))
                .andExpect(jsonPath("$.description").value("Course for beginners"));

        mockMvc.perform(put("/courses/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\n" +
                                "  \"startAt\": \"2022-05-11\",\n" +
                                "  \"endAt\": \"2023-03-23\",\n" +
                                "  \"name\": \"SQL Basics\",\n" +
                                "  \"description\": \"Course for beginners\"\n" +
                                "}"
                        )
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles={"ADMIN"})
    public void deleteCourseTest() throws Exception {
        mockMvc.perform(delete("/courses/1")).andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/courses/2")).andDo(print())
                .andExpect(status().isNotFound());
    }
}