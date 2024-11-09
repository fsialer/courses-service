package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.mapper;

import com.tecylab.ms.courses.app.courses_service.domain.models.Course;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.models.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoursePersistenceMapper {


    CourseEntity toCourseEntity(Course course);

    Course toCourse(CourseEntity courseEntity);

    List<Course> toCourses(List<CourseEntity> courses);

}
