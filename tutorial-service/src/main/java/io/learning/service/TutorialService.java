package io.learning.service;

import io.learning.model.Course;
import io.learning.model.Student;
import io.learning.model.StudentCourse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

@Service
public class TutorialService {

    @Value("${student.service.base.url}")
    private String studentServiceBaseUrl;

    @Value("${course.service.base.url}")
    private String courseServiceBaseUrl;

    public List<StudentCourse> getStudents() {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students").build();
        return (List<StudentCourse>) given().spec(specification).get().as(List.class);
    }

    public StudentCourse getStudent(String email) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/" + email).build();
        return given().spec(specification).get().as(StudentCourse.class);
    }

    public StudentCourse addStudent(Student student) {
        StudentCourse sc = new StudentCourse();
        sc.setName(student.getName());
        sc.setGender(student.getGender());
        sc.setEmail(student.getEmail());
        sc.setPhone(student.getPhone());

        Set<Course> courses = new HashSet<>();
        Set<Long> courseIds = student.getCourses();
        courseIds.forEach(id -> courses.add(this.getCourse(id)));
        sc.setCourses(courses);

        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students")
                .addHeader("Content-Type", "application/json")
                .setBody(sc)
                .build();

        Response response = given().spec(specification).when().post();
        int i = response.statusCode();
        response.prettyPrint();
        return response.as(StudentCourse.class);
    }

    public StudentCourse updateStudent(String email, Student student) {
        StudentCourse sc = new StudentCourse();
        sc.setName(student.getName());
        sc.setGender(student.getGender());
        sc.setEmail(student.getEmail());
        sc.setPhone(student.getPhone());

        Set<Course> courses = new HashSet<>();
        Set<Long> courseIds = student.getCourses();
        courseIds.forEach(id -> courses.add(this.getCourse(id)));
        sc.setCourses(courses);

        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/" + email)
                .addHeader("Content-Type", "application/json")
                .setBody(sc).build();
        return given().spec(specification).when().put().then().extract().as(StudentCourse.class);
    }

    public List<Student> searchStudents(String name) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/search")
                .addQueryParam("name", name)
                .build();
        return (List<Student>) given().spec(specification)
                .when().get().then().extract().as(List.class);
    }

    public void registerCourse(String email, Long courseId) {
        StudentCourse student = this.getStudent(email);
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/" + student.getId() + "/courses/" + courseId).build();
        given().spec(specification).post();
    }

    public void deleteStudent(String email) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/" + email).build();
        given().spec(specification).delete();
    }

    public List<Course> getCourses() {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(courseServiceBaseUrl + "/api/courses").build();
        return (List<Course>) given().spec(specification).get().as(List.class);
    }

    public Course getCourse(Long id) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(courseServiceBaseUrl + "/api/courses/" + id).build();
        return given().spec(specification).when().get().then().extract().as(Course.class);
    }
}
