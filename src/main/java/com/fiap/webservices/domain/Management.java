package com.fiap.webservices.domain;


import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "management_sequence", sequenceName = "SQ_MANAGEMENT", allocationSize = 1)
public class Management {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "management_sequence")
    private int idStudentGrades;
    private Integer portugueseGrade;
    private Integer mathGrade;
    private Integer englishGrade;
    @OneToOne
    private Student student;

    public Management(Integer portugueseGrade, Integer mathGrade, Integer englishGrade, Student student) {
        this.portugueseGrade = portugueseGrade;
        this.mathGrade = mathGrade;
        this.englishGrade = englishGrade;
        this.student = student;
    }

    public Management() {

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
