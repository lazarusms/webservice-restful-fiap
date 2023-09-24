package com.fiap.webservices.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


@Entity
@SequenceGenerator(name = "student", sequenceName = "SQ_STUDENT", allocationSize = 1)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student")
    private int id;
    @ManyToOne
    @JsonBackReference
    private Parent parent;

    @NotBlank(message = "Nome obrigatório!")
    @Size(max = 50)
    private String name;
    private String cpf;
    private LocalDate dateOfBirth;

    public Student(Parent parent, String name, String cpf, LocalDate dateOfBirth) {
        this.parent = parent;
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
    }

    public Student() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}