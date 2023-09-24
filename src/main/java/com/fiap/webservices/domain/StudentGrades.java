package com.fiap.webservices.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "stgrades_sequence", sequenceName = "SQ_STGRADES", allocationSize = 1)
public class StudentGrades {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stgrades_sequence")
    private int idStudentGrades;
    private Integer portugueseGrade;
    private Integer mathGrade;
    private Integer englishGrade;
    @OneToOne
    private Student student;

    public StudentGrades(Integer portugueseGrade, Integer mathGrade, Integer englishGrade, Student student) {
        this.portugueseGrade = portugueseGrade;
        this.mathGrade = mathGrade;
        this.englishGrade = englishGrade;
        this.student = student;
    }

    public StudentGrades() {

    }

    public int getIdStudentGrades() {
        return idStudentGrades;
    }

    public void setIdStudentGrades(int idStudentGrades) {
        this.idStudentGrades = idStudentGrades;
    }

    public int getStudent() {
        return student.getId();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getPortugueseGrade() {
        return portugueseGrade;
    }

    public void setPortugueseGrade(Integer portugueseGrade) {
        this.portugueseGrade = portugueseGrade;
    }

    public Integer getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(Integer mathGrade) {
        this.mathGrade = mathGrade;
    }

    public Integer getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(Integer englishGrade) {
        this.englishGrade = englishGrade;
    }
}
