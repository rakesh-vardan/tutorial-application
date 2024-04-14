CREATE SCHEMA IF NOT EXISTS tutorial;

SET search_path TO tutorial;

CREATE SEQUENCE course_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE student_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE course (
  id BIGINT PRIMARY KEY DEFAULT nextval('course_seq'),
  name VARCHAR(100) NOT NULL,
  description VARCHAR(255) NOT NULL
);

CREATE TABLE student (
  id BIGINT PRIMARY KEY DEFAULT nextval('student_seq'),
  name VARCHAR(100) NOT NULL,
  gender VARCHAR(10) NOT NULL,
  email VARCHAR(100) NOT NULL,
  phone VARCHAR(20) NOT NULL
);

CREATE TABLE student_course (
    student_id BIGINT REFERENCES student(id),
    course_id BIGINT REFERENCES course(id),
    PRIMARY KEY (student_id, course_id)
);