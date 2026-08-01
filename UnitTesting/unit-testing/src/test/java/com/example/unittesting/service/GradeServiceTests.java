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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

        Assertions.assertEquals("B",g);

    }

    @Test
    void testForVoidMethodCalls(){ /// basically used for delete method testing like whether delete method was reacable or not

        System.out.println("testForVoidMethodCalls");
        Mockito.doNothing().when(studentRepo).deleteById(1);
        gradeService.deleteById(1);
        Mockito.verify(studentRepo,Mockito.times(1)).deleteById(1);

    }

    @Test
    void testForPrivateMethodCalls(){
        System.out.println("testForPrivateMethodCalls");
        Method method= null;
        try {
            method = GradeService.class.getDeclaredMethod("validator", String.class,int.class, boolean.class);
            method.setAccessible(true);
            String s= (String) method.invoke(gradeService,"Hello world ",1,false);
            System.out.println(s);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testingForExceptionCatch(){
        System.out.println("testingForExceptionCatch");
        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> {
            gradeService.generateGrade(1);
        });

    }
}
