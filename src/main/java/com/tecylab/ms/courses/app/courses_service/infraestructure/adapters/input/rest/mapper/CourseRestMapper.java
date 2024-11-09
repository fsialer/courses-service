package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.mapper;

import com.tecylab.ms.courses.app.courses_service.domain.models.Course;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.request.CourseCreateRequest;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.response.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CourseRestMapper {
    Course toCourse(CourseCreateRequest request);
    CourseResponse toCourseResponse(Course course);
    List<CourseResponse> toCoursesResponses(List<Course> courses);
}
