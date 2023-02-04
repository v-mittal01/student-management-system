package com.students.restApi.service;

import com.students.restApi.entities.Student;
import com.students.restApi.entities.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAll();

    StudentDTO findById(int studentId);

    boolean addStudent(StudentDTO studentRequest);

    boolean updateStudent(StudentDTO studentRequest);

    boolean deleteStudent(Student student);

}
