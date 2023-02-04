package com.students.restApi.controller;

//import java.util.HashMap;

import com.students.restApi.entities.Student;
import com.students.restApi.entities.StudentDTO;
import com.students.restApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {

    /**
     * NOTE : All the unused imports and commented part in the below script is used
     * to send data to notification_service using RESTemplate.
     */
    @Autowired
    Environment env;

    @Autowired
    private StudentService studentService;

    //private Map<String, String> notification = new HashMap<>();
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/getStudents")
    public List<StudentDTO> home() {
        return studentService.findAll();
    }

    @PostMapping("/addStudent")
    public HttpStatus addStudent(@RequestBody StudentDTO studentRequest) {
        if (studentService.addStudent(studentRequest)) {
            // if value is to be sent in String format
            final String TOPIC = env.getProperty("kafka.producer.topic");
            String message = "Student " + studentRequest.getName() + " Added Successfully! Student_Id = "
                    + studentRequest.getStudentId();
            kafkaTemplate.send(TOPIC, message);
            /*notification.put("name", student.getName());
            notification.put("id", String.valueOf(student.getId()));
            notification.put("operation", "added");
            restTemplate.postForObject("http://localhost:9090/notify",notification,String.class);
            kafkaTemplate.send(TOPIC,new NotifyDTO(student.getId(),student.getName(), "Add"));

            //if value is to be sent in JSON format
            kafkaTemplate.send(TOPIC,student);*/
            return HttpStatus.OK;
        } else
            return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    @PutMapping("/updateStudent")
    public HttpStatus updateStudent(@RequestBody StudentDTO studentRequest) {
        if (studentService.updateStudent(studentRequest)) {
            String message = "Student " + studentRequest.getName() + " Updated Successfully! Student_Id = "
                    + studentRequest.getStudentId();
            final String TOPIC = env.getProperty("kafka.producer.topic");
            kafkaTemplate.send(TOPIC, message);

            /*notification.put("name", student.getName());
            notification.put("id", String.valueOf(student.getId()));
            notification.put("operation", "updated");
            restTemplate.postForObject("http://localhost:9090/notify",notification,String.class);
            kafkaTemplate.send(TOPIC, "[MESSAGE FROM KAFKA] Student "+student.getName()+" Updated Successfully! Student_Id = "+String.valueOf(student.getId()));
            restTemplate.exchange("http://localhost:9090/notify",HttpMethod.POST, notification, HashMap.class);*/

            return HttpStatus.OK;
        } else
            return HttpStatus.NOT_MODIFIED;
    }

    @DeleteMapping("/removeStudent")
    public HttpStatus deleteStudent(@RequestBody Student student) {
        if (studentService.deleteStudent(student)) {
			/*notification.put("name", student.getName());
			notification.put("id", String.valueOf(student.getId()));
			notification.put("operation", "deleted");
            restTemplate.postForObject("http://localhost:9090/notify",notification,String.class);
            kafkaTemplate.send(TOPIC, "[MESSAGE FROM KAFKA] Student "+student.getName()+" Deleted Successfully! Student_Id = "+String.valueOf(student.getId()));*/
            String message = "Student " + student.getName() + " Deleted Successfully! Student_Id = " + student.getStudentId();
            final String TOPIC = env.getProperty("kafka.producer.topic");
            assert TOPIC != null;
            kafkaTemplate.send(TOPIC, message);
            return HttpStatus.OK;
        } else
            return HttpStatus.BAD_REQUEST;
    }

    @GetMapping("/getStudent/{studentId}")
    public StudentDTO getStudent(@PathVariable("studentId") String studentId) {
        return studentService.findById(Integer.parseInt(studentId));
    }
}
