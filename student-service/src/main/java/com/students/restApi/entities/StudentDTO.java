package com.students.restApi.entities;

public class StudentDTO {
    private int studentId;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;

    public StudentDTO(int studentId, String name, int age, String email, String phoneNumber) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public StudentDTO() {
    }

    public static StudentDTO getInstanceOfStudentDTO(Student student) {
        return new StudentDTO(student.getStudentId(), student.getName(), student.getAge(), student.getEmail(), student.getPhoneNumber());
    }

    public static Student getInstanceOfStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAge(), studentDTO.getEmail(), studentDTO.getPhoneNumber());
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
