package com.tecylab.ms.courses.app.courses_service.application.ports.output;

import com.tecylab.ms.courses.app.courses_service.domain.models.Student;

import java.util.Optional;

public interface StudentsOutPutPort {
    Student addStudentToCourse(Long courseId, Long studentId);
    Student removeStudentFromCourse(Long courseId,Long studentId);
    void removeStudentFromCollection(Long studentId);
}
