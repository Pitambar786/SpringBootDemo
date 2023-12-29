package com.demo.student;

import com.demo.student.controller.StudentController;
import com.demo.student.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private StudentController studentController;

    @Override
    public void run(String...args) throws Exception {
        Student student= new Student();
        student.setGender("M");
        student.setFirstName("Advait");
        student.setLastName("Pradhan");
        student.setDob(LocalDate.of(2005,07,07));
        student.setSection("A");
        student.setMark1(50);
        student.setMark2(50);
        student.setMark3(50);
       ResponseEntity entity = studentController.createStudent(student);
       entity.getBody();

    }
}