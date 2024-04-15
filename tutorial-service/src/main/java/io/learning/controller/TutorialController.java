package io.learning.controller;

import io.learning.model.Course;
import io.learning.model.Student;
import io.learning.model.StudentCourse;
import io.learning.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorial")
@CrossOrigin(origins = "http://localhost:4200")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/student-details")
    public ResponseEntity<List<StudentCourse>> getAllStudents() {
        List<StudentCourse> studentList = tutorialService.getStudents();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/student-details/{email}")
    public ResponseEntity<StudentCourse> getStudent(@PathVariable String email) {
        return ResponseEntity.ok(tutorialService.getStudent(email));
    }

    @PostMapping("/student-details")
    public ResponseEntity<StudentCourse> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(tutorialService.addStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("/student-details/{email}")
    public ResponseEntity<StudentCourse> updateStudent(@RequestBody Student student,
                                                 @PathVariable String email) {
        return new ResponseEntity<>(tutorialService.updateStudent(email, student),
                HttpStatus.OK);
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

    @GetMapping("/course-details")
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(tutorialService.getCourses());
    }
}
