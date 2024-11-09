package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.response;

import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private List<Student> students;
    private String timestamp;
}
