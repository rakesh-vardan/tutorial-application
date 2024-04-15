package io.learning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentCourse {

    private Long id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private Set<Course> courses;
}
