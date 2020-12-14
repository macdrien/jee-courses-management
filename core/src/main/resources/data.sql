INSERT INTO courses (title) VALUES ('LO54');
INSERT INTO courses (title) VALUES ('AD50');
INSERT INTO courses (title) VALUES ('SR50');

INSERT INTO locations (city) VALUES ('Belfort');
INSERT INTO locations (city) VALUES ('Mulhouse');
INSERT INTO locations (city) VALUES ('Montbeliard');
INSERT INTO locations (city) VALUES ('Toulouse');

INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2020-09-03', '2021-04-01', NULL, 1, 1);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-01-04', '2021-06-23', 12, 1, 1);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-01-05', '2021-04-10', 30, 1, 3);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-02-01', '2021-06-05', 20, 1, 3);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2020-09-03', '2021-04-01', NULL, 2, 1);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-01-04', '2021-06-23', 12, 2, 1);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-01-05', '2021-04-10', 30, 2, 2);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-02-01', '2021-06-05', 20, 2, 4);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2020-09-03', '2021-04-01', NULL, 3, 1);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-01-04', '2021-06-23', 12, 3, 1);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-01-05', '2021-04-10', 30, 3, 2);
INSERT INTO course_sessions (start_date, end_date, max_students, id_course, id_location) VALUES ('2021-02-01', '2021-06-05', 20, 3, 2);

COMMIT;