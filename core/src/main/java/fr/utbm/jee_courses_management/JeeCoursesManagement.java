package fr.utbm.jee_courses_management;

import fr.utbm.jee_courses_management.controller.CourseController;
import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.util.DataSetup;

public class JeeCoursesManagement {

    public static void main(String[] args) {
        System.out.println("[INFO] Start JEE Courses Management");

        DataSetup.dataSetup();

        CourseController controller = new CourseController();

        for (Course course : controller.getCourses())
            System.out.println(course);
    }
}
