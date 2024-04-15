package io.learning.controller;

import io.learning.model.Student;
import io.learning.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentsService.getStudents();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Student> getStudent(@PathVariable("email") String email) {
        return ResponseEntity.ok(studentsService.getStudent(email));
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student addedStudent = studentsService.addStudent(student);
        return ResponseEntity.ok(addedStudent);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Student> update(@RequestBody Student student,
                                          @PathVariable("email") String email) {
        Student updatedStudent = studentsService.updateStudent(student, email);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delete(@PathVariable("email") String email) {
        studentsService.deleteStudent(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> search(@RequestParam("name") String name) {
        List<Student> studentList = studentsService.searchStudents(name);
        return ResponseEntity.ok(studentList);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public void registerStudentForCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentsService.registerCourse(studentId, courseId);
    }
}
