package com.admiralxy.restful.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "lessons")
@Getter
@Setter
public class Lesson extends BaseEntity {

    @Column(name = "start_at", nullable = false)
    private Time startAt;

    @Column(name = "end_at", nullable = false)
    private Time endAt;

    @Column(name = "day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

}