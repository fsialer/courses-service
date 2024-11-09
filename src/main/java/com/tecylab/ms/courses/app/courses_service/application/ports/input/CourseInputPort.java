package com.tecylab.ms.courses.app.courses_service.application.ports.input;

import java.util.List;

import com.tecylab.ms.courses.app.courses_service.domain.models.Course;

public interface CourseInputPort {

    Course findById(Long id);
    List<Course> findAll();
    Course save(Course course);
    Course update(Long  id,Course course);
    void deleteById(Long id);
}
