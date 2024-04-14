INSERT INTO course (id, name, description)
VALUES
(NEXTVAL('course_seq'), 'Mathematics', 'Learn about numbers, equations, functions, and more.'),
(NEXTVAL('course_seq'), 'Physics', 'Study the nature and properties of matter and energy.'),
(NEXTVAL('course_seq'), 'Chemistry', 'Explore the substances of which matter is composed.'),
(NEXTVAL('course_seq'), 'Biology', 'Understand the structure, function, and growth of living organisms.'),
(NEXTVAL('course_seq'), 'Computer Science', 'Learn about computation and information processing.'),
(NEXTVAL('course_seq'), 'English Literature', 'Study written works in the English language.'),
(NEXTVAL('course_seq'), 'History', 'Explore the past and how it has influenced the present.'),
(NEXTVAL('course_seq'),'Geography', 'Learn about the physical features of the earth and its atmosphere.'),
(NEXTVAL('course_seq'), 'Art', 'Develop creative skills and understanding of visual arts.'),
(NEXTVAL('course_seq'), 'Music', 'Study the art of arranging sounds in time.');

INSERT INTO student (id, name, gender, email, phone)
VALUES
(NEXTVAL('student_seq'), 'John Doe', 'Male', 'john.doe@example.com', '9885448921'),
(NEXTVAL('student_seq'), 'Jane Doe', 'Female', 'jane.doe@example.com', '9898989898');
