package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.mapper;

import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.models.response.StudentClientResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentPersistenceMapper {
    List<Student> toStudents(List<StudentClientResponse> studentClientResponse);
}
