package Streams;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;
    private String name;
    private List<Subject> subjects;

    public Student(int id, String name, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }

    public static class StudentHelper{
        public static List<Student> provideMockStudents(){

            List<Student> students = new ArrayList<>();

            students.add(new Student(1, "Karthik", List.of(
                    new Subject("Maths", 85),
                    new Subject("Physics", 78),
                    new Subject("Chemistry", 82),
                    new Subject("Computer Science", 95),
                    new Subject("English", 88)
            )));

            students.add(new Student(2, "Arun", List.of(
                    new Subject("Maths", 72),
                    new Subject("Physics", 81),
                    new Subject("Chemistry", 69),
                    new Subject("Computer Science", 80),
                    new Subject("English", 75)
            )));

            students.add(new Student(3, "Priya", List.of(
                    new Subject("Maths", 91),
                    new Subject("Physics", 89),
                    new Subject("Chemistry", 94),
                    new Subject("Computer Science", 96),
                    new Subject("English", 90)
            )));

            students.add(new Student(4, "Divya", List.of(
                    new Subject("Maths", 68),
                    new Subject("Physics", 74),
                    new Subject("Chemistry", 77),
                    new Subject("Computer Science", 71),
                    new Subject("English", 83)
            )));

            students.add(new Student(5, "Rahul", List.of(
                    new Subject("Maths", 88),
                    new Subject("Physics", 84),
                    new Subject("Chemistry", 79),
                    new Subject("Computer Science", 92),
                    new Subject("English", 81)
            )));

            students.add(new Student(6, "Sneha", List.of(
                    new Subject("Maths", 76),
                    new Subject("Physics", 86),
                    new Subject("Chemistry", 88),
                    new Subject("Computer Science", 85),
                    new Subject("English", 91)
            )));

            students.add(new Student(7, "Vijay", List.of(
                    new Subject("Maths", 95),
                    new Subject("Physics", 93),
                    new Subject("Chemistry", 90),
                    new Subject("Computer Science", 98),
                    new Subject("English", 87)
            )));

            students.add(new Student(8, "Anjali", List.of(
                    new Subject("Maths", 82),
                    new Subject("Physics", 79),
                    new Subject("Chemistry", 85),
                    new Subject("Computer Science", 89),
                    new Subject("English", 94)
            )));

            students.add(new Student(9, "Surya", List.of(
                    new Subject("Maths", 64),
                    new Subject("Physics", 70),
                    new Subject("Chemistry", 73),
                    new Subject("Computer Science", 67),
                    new Subject("English", 72)
            )));

            students.add(new Student(10, "Meena", List.of(
                    new Subject("Maths", 89),
                    new Subject("Physics", 92),
                    new Subject("Chemistry", 87),
                    new Subject("Computer Science", 90),
                    new Subject("English", 93)
            )));
            return students;
        }
    }
}
