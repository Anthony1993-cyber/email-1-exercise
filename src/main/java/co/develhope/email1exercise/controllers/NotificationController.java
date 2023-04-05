package co.develhope.email1exercise.controllers;

import co.develhope.email1exercise.entities.NotificationDTO;
import co.develhope.email1exercise.entities.Student;
import co.develhope.email1exercise.services.EmailService;
import co.develhope.email1exercise.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NotificationController {

    @Autowired
    StudentService studentService;

    @Autowired
    EmailService emailService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationDTO notificationDTO){
        try{
            Student student = studentService.getStudentById(notificationDTO.getContactId());
            System.out.println("student: " + student);
            if(student == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student not found:(");
            emailService.sendTo(student.getEmail(), notificationDTO.getTitle(), notificationDTO.getText());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The server is broken:(");
        }
    }




}
