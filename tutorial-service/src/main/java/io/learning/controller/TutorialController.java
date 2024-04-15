package io.learning.controller;

import io.learning.model.Student;
import io.learning.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/student-details")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = tutorialService.getStudents();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/student-details/{email}")
    public ResponseEntity<Student> getStudent(@PathVariable String email) {
        return ResponseEntity.ok(tutorialService.getStudent(email));
    }

    @PostMapping("/student-details")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student addedStudent = tutorialService.addStudent(student);
        return ResponseEntity.ok(addedStudent);
    }

    @PutMapping("/student-details/{email}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                                 @PathVariable String email) {
        Student updatedStudent = tutorialService.updateStudent(email, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/student-details/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam("name") String name) {
        List<Student> studentList = tutorialService.searchStudents(name);
        return ResponseEntity.ok(studentList);
    }

    @DeleteMapping("/student-details/{email}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String email) {
        tutorialService.deleteStudent(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/student-details/{email}/course/{courseId}")
    public ResponseEntity<Void> registerStudentForCourse(@PathVariable String email,
                                                         @PathVariable Long courseId) {
        tutorialService.registerCourse(email, courseId);
        return ResponseEntity.ok().build();
    }
}
