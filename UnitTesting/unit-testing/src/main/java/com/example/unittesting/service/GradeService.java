package com.example.unittesting.service;

import com.example.unittesting.entity.Student;
import com.example.unittesting.repository.StudentRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.OptionalDouble;

@Service
public class GradeService {

    @Autowired
    private StudentRepo studentRepo;

    @PostConstruct
    public void postConstruct(){
        System.out.println("@PostConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy");
    }

    public String generateGrade(int id){

        Student s=studentRepo.findById(id).get();
        String grade="F";
        OptionalDouble d=s.getGrades().stream().mapToInt((m)->m).average();
        if(d.isPresent()){
            double m=d.getAsDouble();
            if(m>=90){
                grade="A";
            }
            else if(m<90 && m>=70){
                grade="B";
            }
            else if(m<70 && m>=60){
                grade="C";
            }
        }

        return grade;

    }
}
