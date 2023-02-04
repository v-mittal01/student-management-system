package com.students.restApi.serviceImpl;

import com.students.restApi.entities.Student;
import com.students.restApi.entities.StudentDTO;
import com.students.restApi.repository.StudentRepository;
import com.students.restApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<StudentDTO> findAll() {
        List<StudentDTO> response = new ArrayList<>();
        List<Student> list = studentRepository.findAll();
        for (Student student : list) {
            response.add(StudentDTO.getInstanceOfStudentDTO(student));
        }
        return response;
    }


    @Override
    public boolean addStudent(StudentDTO studentDTO) {
        Student std = studentRepository.findByNameAndEmail(studentDTO.getName(), studentDTO.getEmail());
        if (std == null) {
            studentRepository.addStudent(StudentDTO.getInstanceOfStudent(studentDTO));
            return true;
        }
        return false;
    }

    @Override
    public StudentDTO findById(int studentId) {
        Student student = studentRepository.findById(studentId);
        return (student != null) ? StudentDTO.getInstanceOfStudentDTO(student) : null;
    }

    @Override
    public boolean updateStudent(StudentDTO studentRequest) {
        Student std = studentRepository.findByNameAndEmail(studentRequest.getName(), studentRequest.getEmail());
        if (std != null) {
            Student student = StudentDTO.getInstanceOfStudent(studentRequest);
            student.setStudentId(std.getStudentId());
            studentRepository.updateStudent(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(Student student) {
        Student std = studentRepository.findByNameAndEmail(student.getName(), student.getEmail());
        if (std != null) {
            student.setStudentId(std.getStudentId());
            studentRepository.deleteStudent(student);
            return true;
        }
        return false;
    }

}
