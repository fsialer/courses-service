package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest;

import com.tecylab.ms.courses.app.courses_service.application.ports.input.CourseInputPort;
import com.tecylab.ms.courses.app.courses_service.application.ports.input.StudentInputPort;
import com.tecylab.ms.courses.app.courses_service.domain.models.Course;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.mapper.CourseRestMapper;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.mapper.StudentRestMapper;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.request.CourseCreateRequest;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.response.CourseResponse;
import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.input.rest.models.response.StudentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseRestAdapter {

    private final CourseInputPort courseInputPort;
    private final StudentInputPort studentInputPort;
    private final CourseRestMapper restMapper;
    private final StudentRestMapper studentRestMapper;

    @GetMapping
    public List<CourseResponse> findAll(){
        return restMapper.toCoursesResponses(courseInputPort.findAll());
    }

    @GetMapping("/{id}")
    public CourseResponse findById(@PathVariable Long id){
        return restMapper.toCourseResponse(courseInputPort.findById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> save(@Valid @RequestBody CourseCreateRequest request){
        Course course=courseInputPort.save(restMapper.toCourse(request));
        return ResponseEntity.created(URI.create("/courses/".concat(course.getId().toString()))).build();
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id,@Valid @RequestBody CourseCreateRequest request){
        return restMapper.toCourseResponse(courseInputPort.update(id,restMapper.toCourse(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        courseInputPort.deleteById(id);
    }

    @PutMapping("/course/{courseId}/student/{studentId}")
    public StudentResponse addStudentToCourse(@PathVariable(name ="courseId" ) Long courseId,
                                              @PathVariable(name ="studentId" ) Long studentId){
        return studentRestMapper.toStudentResponse(studentInputPort.addStudentToCourse(courseId,studentId));
    }

    @DeleteMapping("/course/{courseId}/student/{studentId}")
    public StudentResponse removeStudentFromCourse(@PathVariable(name ="courseId" ) Long courseId,
                                                   @PathVariable(name ="studentId" ) Long studentId){
        return studentRestMapper.toStudentResponse(studentInputPort.removeStudentFromCourse(courseId,studentId));
    }

    @DeleteMapping("/remove-student-from-collection/{studentId}")
    public void removeStudentFromCollection(@PathVariable(name = "studentId") Long studentId){
        studentInputPort.removeStudentFromCollection(studentId);
    }







}
