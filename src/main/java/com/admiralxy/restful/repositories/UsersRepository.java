package com.admiralxy.restful.repositories;

import com.admiralxy.restful.entities.Course;
import com.admiralxy.restful.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    Page<User> findByCoursesAsStudentContains(Course coursesAsStudent, Pageable pageable);
    Page<User> findByCoursesAsTeacherContains(Course coursesAsTeacher, Pageable pageable);
}
