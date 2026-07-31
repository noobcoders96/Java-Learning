package com.example.unittesting.controller;

import com.example.unittesting.entity.Student;
import com.example.unittesting.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCOntroller {

    @Autowired
    GradeService gradeService;

    @GetMapping
    public String getGrade(@RequestBody int id){
       return  gradeService.generateGrade(id);
    }
}
