package com.demo.student.util;

import com.demo.student.dto.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student)target;
        if(student.getMark1()<35){
            errors.rejectValue("mark1","error.mark1","Mark1 should be > 35");
        }
        if(student.getMark2()<35){
            errors.rejectValue("mark2","error.mark2","Mark2 should be > 35");
        }
        if(student.getMark3()<35){
            errors.rejectValue("mark3","error.mark3","Mark3 should be > 35");
        }
        LocalDate dob=student.getDob();
        int year=Period.between(dob, LocalDate.now()).getYears();
        if( year<15 || year>20){
            errors.rejectValue("dob","error.age","age should be >15 and < 20");

        }
        String regex
                = "(?:A|B|C)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(student.getSection());

        if(!m.matches()){
            errors.rejectValue("section","error.section","Invalid Section Provided. should be A or B or C");

        }
        String regex1
                = "(?:M|F)$";
        Pattern p1 = Pattern.compile(regex);
        Matcher m1 = p.matcher(student.getSection());

        if(!m1.matches()){
            errors.rejectValue("gender","error.gender","Invalid Gender Provided. should be M or F");

        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }

}
