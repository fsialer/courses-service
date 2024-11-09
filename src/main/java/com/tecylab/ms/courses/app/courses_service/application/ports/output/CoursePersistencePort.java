package com.tecylab.ms.courses.app.courses_service.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.tecylab.ms.courses.app.courses_service.domain.models.Course;

public interface CoursePersistencePort {
    Optional<Course> findById(Long id);
    List<Course> findAll();
    Course save(Course course);
    void deleteById(Long id);

}
