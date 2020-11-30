package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.service.CourseService;
import fr.utbm.jee_courses_management.service.implementation.CourseServiceImpl;

import java.io.Serializable;
import java.util.List;

public class CourseController implements Serializable {

    private final CourseService service;

    public CourseController() {
        service = new CourseServiceImpl();
    }

    public List<Course> getCourses() {
        return service.getCourses();
    }
}
