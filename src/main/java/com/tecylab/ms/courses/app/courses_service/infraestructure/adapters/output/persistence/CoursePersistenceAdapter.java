package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence;

import com.tecylab.ms.courses.app.courses_service.application.ports.output.CoursePersistencePort;
import com.tecylab.ms.courses.app.courses_service.domain.exceptions.CourseNotFoundException;
import com.tecylab.ms.courses.app.courses_service.domain.models.Course;
import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.mapper.CoursePersistenceMapper;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.mapper.StudentPersistenceMapper;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.models.CourseStudent;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.repository.CourseJpaRepository;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.client.StudentFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoursePersistenceAdapter implements CoursePersistencePort {

    private final CourseJpaRepository repository;
    private final CoursePersistenceMapper mapper;
    private final StudentPersistenceMapper studentMapper;
    private final StudentFeignClient client;
    @Override
    public Optional<Course> findById(Long id) {
        return repository.findById(id)
                .map(courseEntity->{
                    List<Long> userIds=courseEntity.getCourseStudentList()
                            .stream()
                            .map(CourseStudent::getStudentId)
                            .toList();

                    List<Student> students=studentMapper.toStudents(client.findByIds(userIds));
                    Course course=mapper.toCourse(courseEntity);
                    course.setStudents(students);
                    return  course;
                });
    }

    @Override
    public List<Course> findAll() {
       return mapper.toCourses(repository.findAll());
    }

    @Override
    public Course save(Course course) {
        return mapper.toCourse(repository.save(mapper.toCourseEntity(course)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

