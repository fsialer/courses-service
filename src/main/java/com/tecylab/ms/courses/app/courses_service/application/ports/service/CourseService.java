package com.tecylab.ms.courses.app.courses_service.application.ports.service;

import com.tecylab.ms.courses.app.courses_service.application.ports.input.CourseInputPort;
import com.tecylab.ms.courses.app.courses_service.application.ports.input.StudentInputPort;
import com.tecylab.ms.courses.app.courses_service.application.ports.output.CoursePersistencePort;
import com.tecylab.ms.courses.app.courses_service.application.ports.output.StudentsOutPutPort;
import com.tecylab.ms.courses.app.courses_service.domain.exceptions.CourseNotFoundException;
import com.tecylab.ms.courses.app.courses_service.domain.models.Course;
import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements CourseInputPort, StudentInputPort {

    private final CoursePersistencePort persistencePort;
    private final StudentsOutPutPort studentsOutPutPort;

    @Override
    public Course findById(Long id) {
        return persistencePort.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public List<Course> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public Course save(Course course) {
        return persistencePort.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        return persistencePort.findById(id).map(
                courseDb->{
                    courseDb.setName(course.getName());
                    return persistencePort.save(courseDb);
                }
        ).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(persistencePort.findById(id).isEmpty()){
            throw  new CourseNotFoundException();
        }
        persistencePort.deleteById(id);
    }

    @Override
    public Student addStudentToCourse(Long courseId, Long studentId) {
        return studentsOutPutPort.addStudentToCourse(courseId,studentId);
    }

    @Override
    public Student removeStudentFromCourse(Long courseId, Long studentId) {
        return studentsOutPutPort.removeStudentFromCourse(courseId,studentId);
    }

    @Override
    public void removeStudentFromCollection(Long studentId) {
        studentsOutPutPort.removeStudentFromCollection(studentId);
    }
}
