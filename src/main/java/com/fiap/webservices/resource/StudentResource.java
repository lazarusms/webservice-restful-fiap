package com.fiap.webservices.resource;

import com.fiap.webservices.domain.Student;
import com.fiap.webservices.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentResource {
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentResource(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudents() {
        Collection<Student> getAllStudents = studentService.getAllStudents();
        return ResponseEntity.ok(getAllStudents);
    }
    @GetMapping(path = "/{studentId}")
    public ResponseEntity<Student> findStudentById(@PathVariable int studentId) {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @PostMapping()
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student registerStudent = studentService.create(student);
        return ResponseEntity.ok(registerStudent);
    }
    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Estudante deletado com sucesso");
    }
    @PutMapping(path = "/{studentId}")
    public ResponseEntity<Student> studentUpdate(
            @PathVariable("studentId") int studentId,
            @RequestBody Student updatedStudent) {
        Student updated = studentService.update(studentId, updatedStudent);
        return ResponseEntity.ok(updated);
    }




}
