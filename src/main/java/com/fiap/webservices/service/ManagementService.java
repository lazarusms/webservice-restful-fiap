package com.fiap.webservices.service;


import com.fiap.webservices.domain.Management;

import java.util.Collection;

public interface ManagementService {
    Management create(Management management);
    Collection<Management> getAllStudentGrades();
    Management getStudentGradesById(int id);
    Management updateGrade(int id, Management management);
    void deleteManagement(int id);
}
