CREATE TABLE IF NOT EXISTS courses (
    id_course INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS locations (
    id_location INTEGER PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS course_sessions (
    id_course_session INTEGER PRIMARY KEY AUTO_INCREMENT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    max_students INTEGER,
    id_course INTEGER NOT NULL,
    id_location INTEGER NOT NULL,
    CONSTRAINT course_session_fk_course FOREIGN KEY (id_course) REFERENCES courses(id_course),
    CONSTRAINT course_session_fk_location FOREIGN KEY (id_location) REFERENCES locations(id_location)
);

CREATE TABLE IF NOT EXISTS clients (
    id_client INTEGER NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(256) NOT NULL,
    lastname VARCHAR(256) NOT NULL,
    address VARCHAR(256) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    email VARCHAR(256),
    id_course_session INTEGER NOT NULL,
    CONSTRAINT client_fk_course_session FOREIGN KEY (id_course_session) REFERENCES course_sessions(id_course_session)
);

COMMIT;