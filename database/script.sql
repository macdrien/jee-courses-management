DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS course_session;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS course;

CREATE TABLE location (
    id SERIAL PRIMARY KEY,
    city VARCHAR(256) NOT NULL
);

CREATE TABLE course (
    code CHAR(10) PRIMARY KEY,
    title VARCHAR(256) NOT NULL
);

CREATE TABLE course_session (
    id SERIAL PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    max_students INTEGER,
    course_code CHAR(10) NOT NULL,
    location_id INTEGER NOT NULL,
    CONSTRAINT course_session_fk_course FOREIGN KEY (course_code) REFERENCES course(code),
    CONSTRAINT course_session_fk_location FOREIGN KEY (location_id) REFERENCES location(id)
);

CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    lastname VARCHAR(100) NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    address VARCHAR(256) NOT NULL,
    phone CHAR(10) NOT NULL,
    email VARCHAR(100),
    course_session_id INTEGER NOT NULL,
    CONSTRAINT client_fk_course_session FOREIGN KEY (course_session_id) REFERENCES course_session(id)
);