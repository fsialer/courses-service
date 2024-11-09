package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.mapper;

import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.models.response.StudentClientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentRestClientMapper {
    Student toStudent(StudentClientResponse studentResponse);
}
