package com.demo.student.service;

import com.demo.student.dto.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    public boolean createStudent(Student student);

    boolean updateStudent(Student student);
}
