package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.repository.CourseRepository;
import fr.utbm.jee_courses_management.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl() {
        repository = new CourseRepository();
    }

    /** @see fr.utbm.jee_courses_management.service.CourseService#getCourses() */
    @Override
    public List<Course> getCourses() {
        return repository.getCourses();
    }

}
