package com.demo.student.service;

import com.demo.student.controller.repository.StudentRepo;
import com.demo.student.dto.Student;
import com.demo.student.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepo;
    @Override
    public boolean createStudent(Student student) {
        calculateTotalAndResult(student);
        Student stu=studentRepo.save(student);
         if(stu!=null) return true;
         else return false;
    }

    @Override
    public boolean updateMarks(Student student) {
        calculateTotalAndResult(student);
        Optional<Student> studentEntity = studentRepo.findById(student.getId());
        Student stu=null;
        if(studentEntity.isPresent()) {
            Student studentEnti = studentEntity.get();

            studentEnti.setMark1(student.getMark1());
            studentEnti.setMark2(student.getMark2());
            studentEnti.setMark3(student.getMark3());
            studentEnti.setResult(student.getResult());
            studentEnti.setAvg(student.getAvg());
            studentEnti.setTotal(student.getTotal());
            stu= studentRepo.save(studentEnti);
        }else{
            throw new StudentNotFoundException("No student records Found with the id:"+student.getId());
        }
        if(stu!=null) return true;
        else return false;
    }

    public void calculateTotalAndResult(Student student){
        int total=student.getMark1()+student.getMark2()+student.getMark3();
        student.setTotal(total);
        student.setAvg(total/3);
        String result=(student.getMark1()>35 && student.getMark2()>35 && student.getMark3()>35)?"Pass":"Fail";
        student.setResult(result);
    }
}
