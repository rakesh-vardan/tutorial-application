package io.learning.service;

import io.learning.model.Course;
import io.learning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}
