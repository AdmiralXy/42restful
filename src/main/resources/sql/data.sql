INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_TEACHER'),
       ('ROLE_STUDENT');

INSERT INTO users (first_name, last_name, login, password, role_id)
VALUES ('Training center', 'Administrator', 'admin', '$2a$12$fTxDxH7FHPAPaP3kD61rb.d1VessI.3Z2XfCWC6vWcLz9eXQ5H48a', 1),
       ('Mia', 'Wong', 'miawong13', '$2a$12$fTxDxH7FHPAPaP3kD61rb.d1VessI.3Z2XfCWC6vWcLz9eXQ5H48a', 2),
       ('Li', 'Ang', 'angan1967', '$2a$12$fTxDxH7FHPAPaP3kD61rb.d1VessI.3Z2XfCWC6vWcLz9eXQ5H48a', 2),
       ('Salome', 'Simoes', 'salommm', '$2a$12$fTxDxH7FHPAPaP3kD61rb.d1VessI.3Z2XfCWC6vWcLz9eXQ5H48a', 3),
       ('Andrew', 'Hayman', 'aandhay', '$2a$12$fTxDxH7FHPAPaP3kD61rb.d1VessI.3Z2XfCWC6vWcLz9eXQ5H48a', 3),
       ('Burch', 'Desiree', 'burch', '$2a$12$fTxDxH7FHPAPaP3kD61rb.d1VessI.3Z2XfCWC6vWcLz9eXQ5H48a', 3),
       ('Harry', 'Daly', 'agharry', '$2a$12$fTxDxH7FHPAPaP3kD61rb.d1VessI.3Z2XfCWC6vWcLz9eXQ5H48a', 3);


INSERT INTO courses (start_at, end_at, name, description)
VALUES ('2023-07-01', '2023-12-31', 'SQL Basics', 'Course about the basics of the SQL language');

INSERT INTO courses_teachers
VALUES (1, 2),
       (1, 3);

INSERT INTO courses_students
VALUES (1, 4),
       (1, 5),
       (1, 6),
       (1, 7);

INSERT INTO lessons (start_at, end_at, day_of_week, course_id, teacher_id)
VALUES ('11:40', '13:20', 'Monday', 1, 2),
       ('14:00', '15:30', 'Wednesday', 1, 3);