package com.students.restApi.repository;

import com.students.restApi.entities.Student;

import java.util.List;

public interface StudentRepository { // USING CUSTOM REPOSITORY INSTEAD OF JPARepository

    List<Student> findAll();

    Student findByNameAndEmail(String name, String email);

    void addStudent(Student student);

    Student findById(int studentId);

    void updateStudent(Student student);

    void deleteStudent(Student student);
}
