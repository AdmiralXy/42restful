package com.admiralxy.restful.repositories;

import com.admiralxy.restful.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Course, Long> {
}
