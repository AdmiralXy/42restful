package com.admiralxy.restful.repositories;

import com.admiralxy.restful.entities.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
    Page<Lesson> findByCourseId(Long courseId, Pageable pageable);
    Optional<Lesson> findByIdAndCourseId(Long id, Long courseId);
}
