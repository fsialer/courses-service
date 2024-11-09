package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseErrorCatalog {
    COURSE_NOT_FOUND("COURSE_ERR_001","Course not found"),
    STUDENT_NOT_FOUND("COURSE_ERR_002","Student not found"),
    COURSE_BAD_PARAMETERS("COURSE_ERR_003","Invalid parameters form created."),
    WEB_CLIENTE_ERROR("COURSE_ERR_004","Error in connection with student-service"),
    INTERNAL_SERVER_ERROR("GENERIC_ERR_001","Internal server error.");

    private final String code ;
    private final String message;

}
