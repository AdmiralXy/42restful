package com.admiralxy.restful.repositories;

import com.admiralxy.restful.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CoursesRepository extends JpaRepository<Course, Long> {
}
