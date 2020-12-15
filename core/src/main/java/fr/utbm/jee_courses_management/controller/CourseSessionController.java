package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.service.CourseSessionService;
import fr.utbm.jee_courses_management.service.implementation.CourseSessionServiceImpl;

import java.io.Serializable;

public class CourseSessionController implements Serializable {

    private final CourseSessionService sessionService;

    public CourseSessionController() {
        sessionService = new CourseSessionServiceImpl();
    }

    public CourseSession getCourseSessionById(Integer id) {
        return sessionService.getCourseSession(id);
    }
}
