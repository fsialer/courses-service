package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.request;

import com.tecylab.ms.courses.app.courses_service.domain.models.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCreateRequest {
    @NotBlank(message = "Field name cannot be null or empty.")
    private String name;
}
