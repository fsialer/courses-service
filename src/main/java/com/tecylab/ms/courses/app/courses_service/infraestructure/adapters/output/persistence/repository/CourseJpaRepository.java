package com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.repository;

import com.tecylab.ms.courses.app.courses_service.infraestructure.adapters.output.persistence.models.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseJpaRepository extends JpaRepository<CourseEntity,Long> {
    //@Query("DELETE FROM CourseStudent cs WHERE cs.studentId:=studentId")
    @Query("DELETE FROM CourseStudent cs WHERE cs.studentId=?1")
    void deleteCourseStudentByStudentId(Long studentId);
}
