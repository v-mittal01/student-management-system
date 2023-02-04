package com.students.restApi.repositoryImpl;

import com.students.restApi.entities.Student;
import com.students.restApi.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional // to roll back all events if ALL the transactions aren't successful
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> findAll() {
        return (List<Student>) entityManager.createQuery("from Student").getResultList();
    }

    @Override
    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findByNameAndEmail(String name, String email) {
        try {
            return (Student) entityManager.createQuery("from Student where name=:name and email=:email")
                    .setParameter("name", name).setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student findById(int studentId) {
        try {
            return (Student) entityManager.createQuery("from Student where studentId=:studentId").setParameter("studentId", studentId)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(entityManager.contains(student) ? student : entityManager.merge(student));
    }
}
