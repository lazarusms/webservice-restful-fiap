package com.fiap.webservices.service.impl;

import com.fiap.webservices.domain.Management;
import com.fiap.webservices.repository.ManagementRepository;
import com.fiap.webservices.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collection;

@Service
public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;

    @Autowired
    public ManagementServiceImpl(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    @Override
    public Management create(Management management) {
        return managementRepository.save(management);
    }

    @Override
    public Collection<Management> getAllStudentGrades() {
        return managementRepository.findAll();
    }

    @Override
    public Management getStudentGradesById(int id) {
        return managementRepository.getStudentGradesByStudentId(id).get();
    }

    @Override
    public Management updateGrade(int id, Management updatestudentGrades) {
        Management management = managementRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("O id informado não existe"));
        checkMathGrade(management, updatestudentGrades.getMathGrade());
        checkEnglishGrade(management, updatestudentGrades.getEnglishGrade());
        checkPortugueseGrade(management, updatestudentGrades.getPortugueseGrade());
        managementRepository.save(management);
        return management;
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
        Management management = managementRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("O ID do aluno informado não existe"));

        int totalGrades = 0;
        int numberOfGrades = 0;

        if (management.getMathGrade() != null) {
            totalGrades += management.getMathGrade();
            numberOfGrades++;
        }

        if (management.getEnglishGrade() != null) {
            totalGrades += management.getEnglishGrade();
            numberOfGrades++;
        }

        if (management.getPortugueseGrade() != null) {
            totalGrades += management.getPortugueseGrade();
            numberOfGrades++;
        }

        if (numberOfGrades == 0) {
            return 0.0;
        }

        Double result = (double) totalGrades / numberOfGrades;
        return formatResult(result);
    }
    public double calculateClassroomAverageGrade() {
        Collection<Management> allStudentGrades = managementRepository.findAll();
        int totalEnglishGrade = 0;
        int totalPortugueseGrade = 0;
        int totalMathGrade = 0;
        int numberOfStudentsWithGrade = 0;

        for (Management management : allStudentGrades) {
            Integer englishGrade = management.getEnglishGrade();
            Integer portugueseGrade = management.getPortugueseGrade();
            Integer mathGrade = management.getMathGrade();

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

        Double result = (double) sumGrades / (numberOfStudentsWithGrade * 3);
        return formatResult(result);
    }

    public Double formatResult(Double result) {
        String format = String.format("%.2f", result).replace(",", ".");
        return Double.parseDouble(format);
    }

    @Override
    public void deleteManagement(int id) {
        managementRepository.deleteById(id);
    }

    public void checkMathGrade(Management management, Integer grade) {
        if (grade != null && !grade.equals(management.getMathGrade())) {
            management.setMathGrade(grade);
        }
    }
    public void checkEnglishGrade(Management management, Integer grade) {
        if (grade != null && !grade.equals(management.getEnglishGrade())) {
            management.setEnglishGrade(grade);
        }
    }
    public void checkPortugueseGrade(Management management, Integer grade) {
        if (grade != null && !grade.equals(management.getPortugueseGrade())) {
            management.setPortugueseGrade(grade);
        }
    }
}
