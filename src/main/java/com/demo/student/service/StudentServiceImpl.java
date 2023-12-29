package com.demo.student.service;

import com.demo.student.controller.repository.StudentRepo;
import com.demo.student.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepo;
    @Override
    public boolean createStudent(Student student) {
         Student stu=studentRepo.save(student);
         if(stu!=null) return true;
         else return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }
}
