package io.learning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    private Long id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private Set<Course> courses;
}
