package com.admiralxy.restful.processors;

import com.admiralxy.restful.controller.CoursesStateController;
import com.admiralxy.restful.entities.Course;
import com.admiralxy.restful.entities.CourseState;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
@AllArgsConstructor
public class CoursesProcessor implements RepresentationModelProcessor<EntityModel<Course>> {

    @Override
    public EntityModel<Course> process(EntityModel<Course> model) {
        CoursesStateController controller = methodOn(CoursesStateController.class);

        if (model.getContent() != null) {
            if (model.getContent().getState().equals(CourseState.Draft)) {
                model.add(linkTo(controller.publish(model.getContent().getId())).withRel(LinkRelation.of("publish")));
            }
        }

        return model;
    }

}
