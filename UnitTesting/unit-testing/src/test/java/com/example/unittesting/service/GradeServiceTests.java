package com.example.unittesting.service;

import com.example.unittesting.entity.Student;
import com.example.unittesting.repository.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTests {

    @InjectMocks
    private GradeService gradeService;

    @Mock
    private StudentRepo studentRepo;

    @Test
    void getGrade() {


        System.out.println("getGrade");

        Student student = new Student();
        student.setId(1);
        student.setName("test");
        student.setGrades(List.of(100,40,50,60,100));

        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.of(student));

        String g=gradeService.generateGrade(1);

        Assertions.assertEquals("A",g);

    }
}
