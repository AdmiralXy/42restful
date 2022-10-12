package com.admiralxy.restful.repositories;

import com.admiralxy.restful.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
