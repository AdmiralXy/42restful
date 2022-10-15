package com.admiralxy.restful;

import com.admiralxy.restful.entities.Course;
import com.admiralxy.restful.entities.CourseState;
import com.admiralxy.restful.repositories.CoursesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class CoursesStateControllerTest {

    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private CoursesRepository coursesRepository;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .apply(documentationConfiguration(restDocumentation)).build();
    }


    @Test
    public void coursePublish() throws Exception {
        Course course = new Course();

        course.setId(1L);
        course.setStartAt(Date.valueOf("2020-12-31").toLocalDate());
        course.setEndAt(Date.valueOf("2021-12-31").toLocalDate());
        course.setName("Spring Boot");
        course.setDescription("Spring Boot course with Spring Data JPA and Spring Security, for beginners and advanced developers");
        course.setState(CourseState.Draft);

        when(coursesRepository.findById(1L)).thenReturn(Optional.of(course));

        when(coursesRepository.save(course)).thenReturn(course);

        assertEquals(course.getState(), CourseState.Draft);

        this.mockMvc.perform(post("/courses/1/publish"))
                .andExpect(status().isOk())
                .andDo(document("courses-publish", courseSnippet()));

        assertEquals(course.getState(), CourseState.Published);
    }

    private ResponseFieldsSnippet courseSnippet() {
        ConstraintDescriptions constraintDescriptions = new ConstraintDescriptions(Course.class);
        return responseFields(
                fieldWithPath("startAt").description("Start date")
                        .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("lastName"))),
                fieldWithPath("endAt").description("End date").optional()
                        .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("middleName"))),
                fieldWithPath("name").description("Name")
                        .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("firstName"))),
                fieldWithPath("description").description("Description")
                        .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("dateOfBirth"))),
                fieldWithPath("state").description("State, can be `Draft` or `Published`")
                        .attributes(key("constraints").value(constraintDescriptions.descriptionsForProperty("siblings"))));
    }

}
