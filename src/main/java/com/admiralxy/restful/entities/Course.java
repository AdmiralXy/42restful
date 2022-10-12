package com.admiralxy.restful.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course extends BaseEntity {

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDate endAt;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "users_courses",
            joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<User> students;

}
