package com.demo.student.dto;

import jakarta.persistence.*;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "FirstName cannot be null")
    @Size(min=3 ,max=30 ,message = "The length of full name must be between 3 and 30 characters.")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "LastName cannot be null")
    @Size(min=3 ,max=30,message = "The length of full name must be between 3 and 30 characters.")
    private String lastName;
    @Column(nullable = false)
    @Past(message = "The date of birth must be in the past.")
    private LocalDate dob;
    @Column(nullable = false)
    private String section;
    @Column(nullable = false)
    private int mark1;
    @Column(nullable = false)
    private int mark2;
    @Column(nullable = false)
    private int mark3;
    @Column(nullable = false)
    private String gender;

    private int total;
    private int avg;
    private String result;


}
