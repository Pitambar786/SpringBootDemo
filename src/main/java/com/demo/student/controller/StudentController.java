package com.demo.student.controller;

import com.demo.student.service.StudentService;
import com.demo.student.util.StudentValidator;
import com.demo.student.util.ValidatorUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.student.dto.Student;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentValidator validator;
    @Autowired
    private ObjectMapper mapper;
    @PostMapping("/create-student")
    public  ResponseEntity createStudent(@RequestBody  Student student) throws JsonProcessingException {
        List<String> error = ValidatorUtil.validate(student,validator);
        String result="";
        if(error != null){
            result = "{\"status\":\"fail\", \"isValidationError\" : \"true\", " +
                    "\"message\":\"Invalida Data provided\", " +
                    "\"records\" : "+mapper.writeValueAsString(error)+"}";
            return ResponseEntity.ok(result);
        }
        boolean created = studentService.createStudent(student);

        return ResponseEntity.ok(created==true?"student created successfully":"Failed to save student");
    }

    @PutMapping("/update-student")
    public  ResponseEntity updateStudent(@Valid @RequestBody  Student student) throws JsonProcessingException {
        validator.setCreateOrUpdate(true);
        List<String> error = ValidatorUtil.validate(student,validator);
        String result="";
        if(error != null){
            result = "{\"status\":\"fail\", \"isValidationError\" : \"true\", " +
                    "\"message\":\"Invalida Data provided\", " +
                    "\"records\" : "+mapper.writeValueAsString(error)+"}";
            return ResponseEntity.ok(result);
        }
        boolean created = studentService.updateMarks(student);

        return ResponseEntity.ok(created==true?"student updated successfully":"Failed to update student");
    }



}
