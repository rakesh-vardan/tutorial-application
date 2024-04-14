package io.learning.service;

import io.learning.model.Student;
import io.learning.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsService {

    @Autowired
    private Environment environment;

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }


    public Student updateStudent(Student updatedStudent, Long id) {
        Student student = studentRepository.findById(id).get();
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
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudents(String name) {
        List<Student> students = this.getStudents();
        List<Student> filteredStudents = new ArrayList<Student>();
        for (Student student : students) {
            if (student.getEmail().contains(name)) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

//    public Student registerCourse(Long studentId, Long courseId) {
//        Student student = studentRepository.findById(studentId).get();
//        Course course = courseRepository.findById(courseId).get();
//        student.getCourses().add(course);
//        return studentRepository.save(student);
//    }
}
