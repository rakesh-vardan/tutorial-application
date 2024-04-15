package io.learning.service;

import io.learning.model.Course;
import io.learning.model.Student;
import io.learning.repository.CourseRepository;
import io.learning.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentsService {

    @Autowired
    private Environment environment;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student addStudent(Student student) {
        Set<Course> courses = student.getCourses().stream()
                .map(course -> courseRepository.findById(course.getId())
                        .orElseThrow(() -> new RuntimeException("Course not found")))
                .collect(Collectors.toSet());
        student.setCourses(courses);
        return studentRepository.save(student);
    }


    public Student updateStudent(Student updatedStudent, String email) {
        Student student = studentRepository.findByEmail(email);
        if (StringUtils.isNotEmpty(updatedStudent.getName())) {
            student.setName(updatedStudent.getName());
        }
        if (StringUtils.isNotEmpty(updatedStudent.getGender())) {
            student.setGender(updatedStudent.getGender());
        }
        if (StringUtils.isNotEmpty(updatedStudent.getEmail())) {
            student.setEmail(updatedStudent.getEmail());
        }
        if (StringUtils.isNotEmpty(updatedStudent.getPhone())) {
            student.setPhone(updatedStudent.getPhone());
        }
        Set<Course> courses = updatedStudent.getCourses().stream()
                .map(course -> courseRepository.findById(course.getId())
                        .orElseThrow(() -> new RuntimeException("Course not found")))
                .collect(Collectors.toSet());
        student.setCourses(courses);
        return studentRepository.save(student);
    }

    public void deleteStudent(String email) {
        Student student = studentRepository.findByEmail(email);
        studentRepository.delete(student);
    }

    public List<Student> searchStudents(String name) {
        List<Student> students = this.getStudents();
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().contains(name)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    public Student registerCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        return studentRepository.save(student);
    }
}
