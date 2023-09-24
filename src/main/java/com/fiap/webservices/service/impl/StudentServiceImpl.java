package com.fiap.webservices.service.impl;

import com.fiap.webservices.domain.Parent;
import com.fiap.webservices.domain.Student;
import com.fiap.webservices.repository.ParentRepository;
import com.fiap.webservices.repository.StudentRepository;
import com.fiap.webservices.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ParentRepository parentRepository) {
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
    }

    @Override
    @Transactional
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student update(int id, Student student) {
        return null;
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);

    }
    public void checkIfParentExistsByCpf(String cpf) {
        Parent parent = parentRepository.findParentByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found for CPF: " + cpf));


    }
}
