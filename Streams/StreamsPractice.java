package Streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsPractice {

    public static void main(String[] args) {
    List<Student> students = Student.StudentHelper.provideMockStudents();

        //* Print all students.
        //students.forEach((student -> System.out.println(student.getName())));

        //Get the names of all students using map().
        System.out.println("===Printing names of student using Map functionality===");
        students.stream()
                .map(student -> student.getName())
                .collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("=============");

        //Find all students whose name starts with "A".
        System.out.println("===Find all students whose name starts with \"A\"===");
        students.stream()
                .map(student -> student.getName())
                .filter(name->name.startsWith("A"))
                .forEach(name->System.out.println(name));
        System.out.println("=============");

        //Sort students by name.
        System.out.println("Sort students by name---Asc");
        students.stream()
                .map(student -> student.getName())
                .sorted()
                .forEach(name->System.out.println(name));
        System.out.println("=============");

        System.out.println("Sort students by name---Desc");
        students.stream()
                .map(student -> student.getName())
                .sorted(Comparator.reverseOrder())
                .forEach(name->System.out.println(name));
        System.out.println("=============");

        //Count the total number of students.
        System.out.println("Count the total number of students.");
        System.out.println(students.stream().count());
        System.out.println("=============");

        //Get all subjects from all students using flatMap().
        System.out.println("Get all subjects from all students using flatMap()");
        System.out.println(students.stream()
                        .flatMap(student->student.getSubjects().stream()).count());
        System.out.println("=============");

        System.out.println("Get all subjects from all students using flatMap()-Unique");
        System.out.println(students.stream()
                .flatMap(student->student.getSubjects().stream())
                .map(subject->subject.getName())
                .distinct()
                .count());
        System.out.println("=============");

        //Find the total marks obtained by each student.
        System.out.println("Find the total marks obtained by each student.");
        students.stream()
                .forEach(student -> {
                    System.out.println(student.getSubjects().stream().mapToInt(subject->subject.getMarks()).sum()+" "+student.getName());
                });
        System.out.println("=============");
        //Find students whose average is greater than 80.

        System.out.println("Find students whose average is greater than 80.");
        students.stream()
                        .forEach(student -> {
                            student.getSubjects().stream()
                                    .mapToInt(subject->subject.getMarks())
                                    .average()
                                    .ifPresent(average->{
                                        if (average > 80.0) {
                                            System.out.println(average+" "+student.getName());
                                        }
                                    });
                        });
        System.out.println("=============");
        //Sort students based on their total marks.

        //Sort students based on their total marks.
        System.out.println("Sort students based on their total marks.");
        List<Student>markSorted=students.stream().sorted(
                Comparator.comparingInt(
                        student->{
                            return student.getSubjects()
                                    .stream()
                                    .mapToInt(subject->subject.getMarks())
                                    .sum() * -1;
                        }
                )
        ).collect(Collectors.toList());
        markSorted.forEach(student -> {
            System.out.println(student.getSubjects().stream().mapToInt(subject->subject.getMarks()).sum()+" "+student.getName());
        });

        System.out.println("=============");
    }
}



