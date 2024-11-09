package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient;

import com.tecylab.ms.courses.app.courses_service.application.ports.output.StudentsOutPutPort;
import com.tecylab.ms.courses.app.courses_service.domain.exceptions.CourseNotFoundException;
import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.models.CourseStudent;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.repository.CourseJpaRepository;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.client.StudentFeignClient;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.mapper.StudentRestClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentRestClientAdapter implements StudentsOutPutPort {

    private final StudentFeignClient client;
    private final CourseJpaRepository repository;
    private final StudentRestClientMapper clientMapper;

    @Override
    public Student addStudentToCourse(Long courseId, Long studentId) {
        return repository.findById(courseId)
                .map(courseEntity -> {
                    Student student=clientMapper.toStudent(client.findById(studentId));
                    CourseStudent courseStudent=new CourseStudent();
                    courseStudent.setStudentId(student.getId());
                    courseEntity.addCourseStudent(courseStudent);
                    repository.save(courseEntity);
                    return student;
                }).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public Student removeStudentFromCourse(Long courseId, Long studentId) {
        return repository.findById(courseId)
                .map(courseEntity -> {
                    Student student=clientMapper.toStudent(client.findById(studentId));
                    List<Long> studentIds=courseEntity.getCourseStudentList()
                            .stream()
                            .map(CourseStudent::getStudentId)
                            .toList();
                    boolean isRegistered=studentIds.stream()
                            .filter(id->id.equals(studentId))
                            .anyMatch(id->id.equals(student.getId()));
                    if(!isRegistered){
                        throw  new RuntimeException("Student dont registered.");
                    }

                    CourseStudent courseStudent=new CourseStudent();
                    courseStudent.setStudentId(student.getId());
                    courseEntity.removeCourseStudent(courseStudent);
                    repository.save(courseEntity);
                    return student;

                }).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public void removeStudentFromCollection(Long studentId) {
        repository.deleteCourseStudentByStudentId(studentId);
    }
}
