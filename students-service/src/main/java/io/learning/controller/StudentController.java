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

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        Student student = studentsService.getStudent(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student addedStudent = studentsService.addStudent(student);
        return ResponseEntity.ok(addedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@RequestBody Student student,
                                          @PathVariable("id") Long id) {
        Student updatedStudent = studentsService.updateStudent(student, id);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        studentsService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> search(@RequestParam("name") String name) {
        List<Student> studentList = studentsService.searchStudents(name);
        return ResponseEntity.ok(studentList);
    }
}
