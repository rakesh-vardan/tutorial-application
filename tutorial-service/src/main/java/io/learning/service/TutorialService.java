package io.learning.service;

import io.learning.model.Student;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.restassured.RestAssured.given;

@Service
public class TutorialService {

    @Value("${student.service.base.url}")
    private String studentServiceBaseUrl;

    public List<Student> getStudents() {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students").build();
        return (List<Student>) given().spec(specification).get().as(List.class);
    }

    public Student getStudent(String email) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/" + email).build();
        return given().spec(specification).get().as(Student.class);
    }

    public Student addStudent(Student student) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students")
                .addHeader("Content-Type", "application/json")
                .setBody(student).build();
        return given().spec(specification).when().post().then().extract().as(Student.class);
    }

    public Student updateStudent(String email, Student student) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/" + email)
                .addHeader("Content-Type", "application/json")
                .setBody(student).build();
        return given().spec(specification).when().put().then().extract().as(Student.class);
    }

    public List<Student> searchStudents(String name) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/search?name=" + name).build();
        return (List<Student>)given().spec(specification).get();
    }

    public void registerCourse(String email, Long courseId) {
        Student student = this.getStudent(email);
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/" + student.getId() + "/courses/" + courseId).build();
        given().spec(specification).post();
    }

    public void deleteStudent(String email) {
        RequestSpecification specification = new RequestSpecBuilder()
                .setBaseUri(studentServiceBaseUrl + "/api/students/" + email).build();
        given().spec(specification).delete();
    }
}
