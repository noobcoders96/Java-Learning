package com.example.unittesting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Objects;

@Entity
public class Student {

    @Id
    private int id;
    private String name;
    private List<Integer> grades;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grades=" + grades +
                '}';
    }

    public Student() {
    }

    public Student(String name, int id, List<Integer> grades) {
        this.name = name;
        this.id = id;
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(grades, student.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, grades);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        return grades;
    }
}
