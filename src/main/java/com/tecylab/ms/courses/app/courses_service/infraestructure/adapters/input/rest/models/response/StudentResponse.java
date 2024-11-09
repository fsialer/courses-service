package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private String email;
}
