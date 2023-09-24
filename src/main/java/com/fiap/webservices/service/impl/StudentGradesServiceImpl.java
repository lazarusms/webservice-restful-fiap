package com.fiap.webservices.service.impl;

import com.fiap.webservices.domain.StudentGrades;
import com.fiap.webservices.repository.StudentGradesRepository;
import com.fiap.webservices.service.StudentGradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentGradesServiceImpl implements StudentGradesService {

    private final StudentGradesRepository studentGradesRepository;

    @Autowired
    public StudentGradesServiceImpl(StudentGradesRepository studentGradesRepository) {
        this.studentGradesRepository = studentGradesRepository;
    }

    @Override
    public StudentGrades create(StudentGrades studentGrades) {
        return studentGradesRepository.save(studentGrades);
    }

    @Override
    public Collection<StudentGrades> getAllStudentGrades() {
        return studentGradesRepository.findAll();
    }

    @Override
    public StudentGrades getStudentGradesById(int id) {
        return studentGradesRepository.getStudentGradesByStudentId(id).get();
    }

    @Override
    public StudentGrades updateGrade(int id, StudentGrades updatestudentGrades) {
        StudentGrades studentGrades = studentGradesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("O id informado não existe"));
        checkMathGrade(studentGrades, updatestudentGrades.getMathGrade());
        checkEnglishGrade(studentGrades, updatestudentGrades.getEnglishGrade());
        checkPortugueseGrade(studentGrades, updatestudentGrades.getPortugueseGrade());
        studentGradesRepository.save(studentGrades);
        return studentGrades;
    }
    public String feedbackGrade(int studentId) {
        Double studentAverage = calculateStudentAverageGrade(studentId);
        Double classroomAverage = calculateClassroomAverageGrade();

        if (studentAverage > classroomAverage) {
            return "O aluno está ACIMA DA MÉDIA da sala de aula.";
        } else if (studentAverage.equals(classroomAverage)) {
            return "O aluno está NA MÉDIA da sala de aula.";
        } else {
            return "O aluno está ABAIXO DA MÉDIA da sala de aula.";
        }
    }
    public double calculateStudentAverageGrade(int studentId) {
        StudentGrades studentGrades = studentGradesRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("O ID do aluno informado não existe"));

        int totalGrades = 0;
        int numberOfGrades = 0;

        if (studentGrades.getMathGrade() != null) {
            totalGrades += studentGrades.getMathGrade();
            numberOfGrades++;
        }

        if (studentGrades.getEnglishGrade() != null) {
            totalGrades += studentGrades.getEnglishGrade();
            numberOfGrades++;
        }

        if (studentGrades.getPortugueseGrade() != null) {
            totalGrades += studentGrades.getPortugueseGrade();
            numberOfGrades++;
        }

        if (numberOfGrades == 0) {
            return 0.0;
        }

        return (double) totalGrades / numberOfGrades;
    }
    public double calculateClassroomAverageGrade() {
        Collection<StudentGrades> allStudentGrades = studentGradesRepository.findAll();
        int totalEnglishGrade = 0;
        int totalPortugueseGrade = 0;
        int totalMathGrade = 0;
        int numberOfStudentsWithGrade = 0;

        for (StudentGrades studentGrades : allStudentGrades) {
            Integer englishGrade = studentGrades.getEnglishGrade();
            Integer portugueseGrade = studentGrades.getPortugueseGrade();
            Integer mathGrade = studentGrades.getMathGrade();

            if (englishGrade != null) {
                totalEnglishGrade += englishGrade;
            }

            if (portugueseGrade != null) {
                totalPortugueseGrade += portugueseGrade;
            }

            if (mathGrade != null) {
                totalMathGrade += mathGrade;
            }

            numberOfStudentsWithGrade++;
        }

        int sumGrades = totalEnglishGrade + totalMathGrade + totalPortugueseGrade;

        if (numberOfStudentsWithGrade == 0) {
            return 0.0;
        }

        return (double) sumGrades / (numberOfStudentsWithGrade * 3);
    }



    @Override
    public void deleteParent(int id) {
        studentGradesRepository.deleteById(id);
    }

    public void checkMathGrade(StudentGrades studentGrades, Integer grade) {
        if (grade != null && !grade.equals(studentGrades.getMathGrade())) {
            studentGrades.setMathGrade(grade);
        }
    }
    public void checkEnglishGrade(StudentGrades studentGrades, Integer grade) {
        if (grade != null && !grade.equals(studentGrades.getEnglishGrade())) {
            studentGrades.setEnglishGrade(grade);
        }
    }
    public void checkPortugueseGrade(StudentGrades studentGrades, Integer grade) {
        if (grade != null && !grade.equals(studentGrades.getPortugueseGrade())) {
            studentGrades.setPortugueseGrade(grade);
        }
    }
}
