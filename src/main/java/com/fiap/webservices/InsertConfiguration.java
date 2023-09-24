package com.fiap.webservices;

import com.fiap.webservices.domain.Parent;
import com.fiap.webservices.domain.Student;
import com.fiap.webservices.repository.ParentRepository;
import com.fiap.webservices.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.AUGUST;

@Configuration
public class InsertConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(ParentRepository parentRepository, StudentRepository studentRepository) {
        return args -> {
            Parent newParent = new Parent("Marcos",
                    LocalDate.of(1980, Month.AUGUST, 15),
                    "12345678912",
                    "1198943281",
                    "marcosemail@marcos.com",
                    List.of());
            parentRepository.save(newParent);
            Student newStudent = new Student(
                    newParent,
                    "Victoria",
                    "12345678913",
                    LocalDate.of(2000, Month.DECEMBER, 11));
            studentRepository.save(newStudent);
        };

    }
}
