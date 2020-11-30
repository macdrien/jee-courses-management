package fr.utbm.jee_courses_management.service;

import fr.utbm.jee_courses_management.entity.Course;

import java.io.Serializable;
import java.util.List;

public interface CourseService extends Serializable {

    /**
     * @return All courses. The return can be empty but never null.
     */
    public List<Course> getCourses();
}
