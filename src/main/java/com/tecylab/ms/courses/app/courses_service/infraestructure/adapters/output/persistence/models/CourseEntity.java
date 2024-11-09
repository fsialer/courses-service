package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.models;

import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinColumn(name = "course_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseStudent> courseStudentList;

    public CourseEntity() {
        this.courseStudentList = new ArrayList<>();
    }

    public void addCourseStudent(CourseStudent courseStudent){
        this.courseStudentList.add(courseStudent);
    }

    public void removeCourseStudent(CourseStudent courseStudent){
        this.courseStudentList.remove(courseStudent);
    }
}
