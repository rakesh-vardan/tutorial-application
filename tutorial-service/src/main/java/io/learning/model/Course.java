package io.learning.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {

    private Long id;
    private String name;
    private String description;
}
