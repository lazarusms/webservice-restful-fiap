package com.fiap.webservices.resource;

import com.fiap.webservices.domain.Parent;
import com.fiap.webservices.domain.StudentGrades;
import com.fiap.webservices.service.impl.StudentGradesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/grades")
public class StudentGradesResource {
    private final StudentGradesServiceImpl studentGradesService;

    @Autowired
    public StudentGradesResource(StudentGradesServiceImpl studentGradesService) {
        this.studentGradesService = studentGradesService;
    }


    @PostMapping()
    public ResponseEntity<StudentGrades> registerGrades(@RequestBody StudentGrades studentGrades) {
        StudentGrades registerStudentGrade = studentGradesService.create(studentGrades);
        return ResponseEntity.ok(registerStudentGrade);
    }
    @GetMapping()
    public ResponseEntity<Collection<StudentGrades>> getStudentGrades() {
        Collection<StudentGrades> getAllStudentGrades = studentGradesService.getAllStudentGrades();
        return ResponseEntity.ok(getAllStudentGrades);
    }

   @GetMapping("{studentId}")
    public ResponseEntity<StudentGrades> getStudentGradesByStudentId(@PathVariable int studentId) {
       StudentGrades studentGradesById = studentGradesService.getStudentGradesById(studentId);
       return ResponseEntity.ok(studentGradesById);
    }


    @GetMapping("/average/{studentId}")
    public ResponseEntity<Double> getStudentAverageById(@PathVariable int studentId) {
        Double studentAverage = studentGradesService.calculateStudentAverageGrade(studentId);
        return ResponseEntity.ok(studentAverage);
    }

    @GetMapping("/classroom")
    public ResponseEntity<Double> getClassroomAverage() {
        Double classroomAverage = studentGradesService.calculateClassroomAverageGrade();
        return ResponseEntity.ok(classroomAverage);
    }

    @GetMapping("/feedback/{studentId}")
    public ResponseEntity<String> getStudentAverageFeedbackById(@PathVariable int studentId) {
        String feedback = studentGradesService.feedbackGrade(studentId);
        return ResponseEntity.ok(feedback);
    }
    @DeleteMapping("/{studentGradeId}")
    public ResponseEntity<String> deleteStudentGrades(@PathVariable int studentGradeId) {
        studentGradesService.deleteParent(studentGradeId);
        return ResponseEntity.ok("As notas foram deletadas com sucesso");
    }

    @PutMapping(path = "/{studentGradeId}")
    public ResponseEntity<StudentGrades> clientUpdate(
            @PathVariable("studentGradeId") int studentGradeId,
            @RequestBody StudentGrades updatedStudentGrades) {
        StudentGrades updated = studentGradesService.updateGrade(studentGradeId, updatedStudentGrades);
        return ResponseEntity.ok(updated);
    }
}
