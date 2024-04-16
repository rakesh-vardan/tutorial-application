package io.learning.controller;

import io.learning.model.Student;
import io.learning.service.StudentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Students not found")
    })
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentsService.getStudents();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/{email}")
    @Operation(summary = "Get student by email")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Student> getStudent(@PathVariable("email") String email) {
        return ResponseEntity.ok(studentsService.getStudent(email));
    }

    @PostMapping
    @Operation(summary = "Add new student")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Student created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student addedStudent = studentsService.addStudent(student);
        return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    @Operation(summary = "Update student")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student updated"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Student> update(@RequestBody Student student,
                                          @PathVariable("email") String email) {
        Student updatedStudent = studentsService.updateStudent(student, email);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete student")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Student deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Void> delete(@PathVariable("email") String email) {
        studentsService.deleteStudent(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search students")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Students not found")
    })
    public ResponseEntity<List<Student>> search(@RequestParam("name") String name) {
        List<Student> studentList = studentsService.searchStudents(name);
        return ResponseEntity.ok(studentList);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    @Operation(summary = "Register student for course")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student registered for course"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student or course not found")
    })
    public void registerStudentForCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentsService.registerCourse(studentId, courseId);
    }
}
