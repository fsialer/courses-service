package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private String email;
}
