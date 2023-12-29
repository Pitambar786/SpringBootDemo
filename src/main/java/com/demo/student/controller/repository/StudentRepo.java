package com.demo.student.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.student.dto.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

}
