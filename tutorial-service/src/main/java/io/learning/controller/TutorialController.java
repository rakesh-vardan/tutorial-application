package io.learning.controller;

import io.learning.model.Course;
import io.learning.model.Student;
import io.learning.model.StudentCourse;
import io.learning.service.TutorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Students not found")
    })
    public ResponseEntity<List<StudentCourse>> getAllStudents() {
        List<StudentCourse> studentList = tutorialService.getStudents();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/student-details/{email}")
    @Operation(summary = "Get student by email")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<StudentCourse> getStudent(@PathVariable String email) {
        return ResponseEntity.ok(tutorialService.getStudent(email));
    }

    @PostMapping("/student-details")
    @Operation(summary = "Add new student")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Student created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Student already exists")
    })
    public ResponseEntity<StudentCourse> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(tutorialService.addStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("/student-details/{email}")
    @Operation(summary = "Update student")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student updated"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<StudentCourse> updateStudent(@RequestBody Student student,
                                                 @PathVariable String email) {
        return new ResponseEntity<>(tutorialService.updateStudent(email, student),
                HttpStatus.OK);
    }

    @GetMapping("/student-details/search")
    @Operation(summary = "Search students")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Students found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Students not found")
    })
    public ResponseEntity<List<Student>> searchStudents(@RequestParam("name") String name) {
        List<Student> studentList = tutorialService.searchStudents(name);
        return ResponseEntity.ok(studentList);
    }

    @DeleteMapping("/student-details/{email}")
    @Operation(summary = "Delete student")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student deleted"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Void> deleteStudent(@PathVariable String email) {
        tutorialService.deleteStudent(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/student-details/{email}/course/{courseId}")
    @Operation(summary = "Register student for course")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Student registered for course"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student or course not found")
    })
    public ResponseEntity<Void> registerStudentForCourse(@PathVariable String email,
                                                         @PathVariable Long courseId) {
        tutorialService.registerCourse(email, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/course-details")
    @Operation(summary = "Get all courses")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Courses found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Courses not found")
    })
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(tutorialService.getCourses());
    }
}
