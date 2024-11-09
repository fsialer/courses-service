package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest;

import com.tecylab.ms.courses.app.courses_service.domain.exceptions.CourseNotFoundException;
import com.tecylab.ms.courses.app.courses_service.domain.exceptions.StudentNotFoundException;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.enums.ErrorType;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.response.ErrorResponse;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Collections;

import static com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.utils.CourseErrorCatalog.*;


@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseNotFoundException.class)
    public ErrorResponse handleCourseNotFoundException(){
        log.error("Course dont found");
        return ErrorResponse.builder()
                .code(COURSE_NOT_FOUND.getCode())
                .type(ErrorType.FUNCTIONAL)
                .genericMessage(COURSE_NOT_FOUND.getMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public ErrorResponse handleStudentNotFoundException(){
        log.error("Student dont found");
        return ErrorResponse.builder()
                .code(STUDENT_NOT_FOUND.getCode())
                .type(ErrorType.FUNCTIONAL)
                .genericMessage(STUDENT_NOT_FOUND.getMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Bad request");
        BindingResult result=e.getBindingResult();
        return ErrorResponse.builder()
                .code(COURSE_BAD_PARAMETERS.getCode())
                .type(ErrorType.FUNCTIONAL)
                .genericMessage(COURSE_BAD_PARAMETERS.getMessage())
                .timestamp(LocalDate.now().toString())
                .details(result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList())
                .build();
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException e){
        ErrorResponse errorResponse=ErrorResponse.builder()
                .code(WEB_CLIENTE_ERROR.getCode())
                .type(ErrorType.FUNCTIONAL)
                .genericMessage(WEB_CLIENTE_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();
        return ResponseEntity.status(e.status())
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e){
        return ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.getCode())
                .type(ErrorType.SYSTEM)
                .genericMessage(INTERNAL_SERVER_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();

    }

}
