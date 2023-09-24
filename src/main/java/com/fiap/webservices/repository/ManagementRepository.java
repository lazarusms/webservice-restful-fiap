package com.fiap.webservices.repository;

import com.fiap.webservices.domain.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ManagementRepository extends JpaRepository<Management, Integer> {

     @Query("SELECT g FROM Management g WHERE g.idStudentGrades = :id")
    Optional<Management>getStudentGradesByStudentId(@Param("id") int id);

}
