package com.fiap.webservices.resource;

import com.fiap.webservices.domain.Management;
import com.fiap.webservices.service.impl.ManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/management")
public class ManagementResource {
    private final ManagementServiceImpl managementService;


    @Autowired
    public ManagementResource(ManagementServiceImpl managementService) {
        this.managementService = managementService;
    }


    @PostMapping()
    public ResponseEntity<Management> registerGrades(@RequestBody Management management) {
        Management registerStudentGrade = managementService.create(management);
        return ResponseEntity.ok(registerStudentGrade);
    }
    @GetMapping()
    public ResponseEntity<Collection<Management>> getStudentGrades() {
        Collection<Management> getAllStudentGrades = managementService.getAllStudentGrades();
        return ResponseEntity.ok(getAllStudentGrades);
    }

   @GetMapping("{studentId}")
    public ResponseEntity<Management> getStudentGradesByStudentId(@PathVariable int studentId) {
       Management managementById = managementService.getStudentGradesById(studentId);
       return ResponseEntity.ok(managementById);
    }


    @GetMapping("/average/{studentId}")
    public ResponseEntity<Double> getStudentAverageById(@PathVariable int studentId) {
        Double studentAverage = managementService.calculateStudentAverageGrade(studentId);
        return ResponseEntity.ok(studentAverage);
    }

    @GetMapping("/classroom")
    public ResponseEntity<Double> getClassroomAverage() {
        Double classroomAverage = managementService.calculateClassroomAverageGrade();
        return ResponseEntity.ok(classroomAverage);
    }

    @GetMapping("/feedback/{studentId}")
    public ResponseEntity<String> getStudentAverageFeedbackById(@PathVariable int studentId) {
        String feedback = managementService.feedbackGrade(studentId);
        return ResponseEntity.ok(feedback);
    }
    @DeleteMapping("/{studentGradeId}")
    public ResponseEntity<String> deleteStudentGrades(@PathVariable int studentGradeId) {
        managementService.deleteManagement(studentGradeId);
        return ResponseEntity.ok("As notas foram deletadas com sucesso");
    }

    @PutMapping(path = "/{studentGradeId}")
    public ResponseEntity<Management> clientUpdate(
            @PathVariable("studentGradeId") int studentGradeId,
            @RequestBody Management updatedManagement) {
        Management updated = managementService.updateGrade(studentGradeId, updatedManagement);
        return ResponseEntity.ok(updated);
    }
}
