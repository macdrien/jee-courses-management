package fr.utbm.jee_courses_management.util;

import fr.utbm.jee_courses_management.entity.CourseSession;

import java.time.LocalDate;

public class CourseSessionUtil {

    public static final Integer ID = 1;

    public static final LocalDate STARTING_DATE = LocalDate.MIN;

    public static final LocalDate ENDING_DATE = LocalDate.MAX;

    public static final Integer MAX_STUDENTS = 50;

    public static final Integer CLIENT_NUMBER = 5;

    public static final CourseSession COURSE_SESSION = new CourseSession(ID, STARTING_DATE, ENDING_DATE, MAX_STUDENTS,
            null, null, null, CLIENT_NUMBER);
}
