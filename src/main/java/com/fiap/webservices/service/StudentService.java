package com.fiap.webservices.service;

import com.fiap.webservices.domain.Parent;
import com.fiap.webservices.domain.Student;

import java.util.Collection;

public interface StudentService {
    Student create(Student student);
    Collection<Student> getAllStudents();
    Student getStudentById(int id);
    Student update(int id, Student student);
    void deleteStudent(int id);
}
