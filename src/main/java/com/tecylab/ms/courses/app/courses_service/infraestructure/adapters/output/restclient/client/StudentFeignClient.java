package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.client;

import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.restclient.models.response.StudentClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "students-service",url = "${students-service.url}")
public interface StudentFeignClient {
    @GetMapping("students/{id}")
    StudentClientResponse findById(@PathVariable Long id);

    @GetMapping("students/find-by-ids")
    List<StudentClientResponse> findByIds(@RequestParam List<Long> ids);

    @DeleteMapping("student/{id}")
    void deteleById(@PathVariable Long id);
}
