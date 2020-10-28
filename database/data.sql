INSERT INTO location (city) VALUES ('belfort');

INSERT INTO course (code, title) VALUES ('FISA_ISI21', 'Formation d''Ingénieur sous Statut Apprenti en Informatique et Systèmes d''Informations Promotion 2021');

INSERT INTO course_session (start_date, end_date, max_students, course_code, location_id) VALUES (
    '03/09/2018',
    '02/09/2021',
    28,
    'FISA_ISI21',
    1
);

INSERT INTO client (lastname, firstname, address, phone, course_session_id) VALUES (
    'John', 'Doe', '15 rue des Cimes 90000 BELFORT', '0123456789', 1
);