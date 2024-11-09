package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.mapper;

import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StudentRestMapper {
    StudentResponse toStudentResponse(Student student);
}
