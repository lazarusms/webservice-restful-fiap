package com.fiap.webservices.service;


import com.fiap.webservices.domain.StudentGrades;

import java.util.Collection;

public interface StudentGradesService {
    StudentGrades create(StudentGrades studentGrades);
    Collection<StudentGrades> getAllStudentGrades();
    StudentGrades getStudentGradesById(int id);
    StudentGrades updateGrade(int id, StudentGrades studentGrades);
    void deleteParent(int id);
}
