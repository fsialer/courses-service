package com.tecylab.ms.courses.app.courses_service.application.ports.input;

import java.util.Optional;

import com.tecylab.ms.courses.app.courses_service.domain.models.Student;

public interface StudentInputPort {
    Student addStudentToCourse(Long courseId,Long studentId);
    Student removeStudentFromCourse(Long courseId,Long studentId);
    void removeStudentFromCollection(Long studentId);
}
