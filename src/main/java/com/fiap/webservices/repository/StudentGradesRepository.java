package com.fiap.webservices.repository;

import com.fiap.webservices.domain.Parent;
import com.fiap.webservices.domain.StudentGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentGradesRepository extends JpaRepository<StudentGrades, Integer> {

     @Query("SELECT g FROM StudentGrades g WHERE g.idStudentGrades = :id")
    Optional<StudentGrades>getStudentGradesByStudentId(@Param("id") int id);

}
