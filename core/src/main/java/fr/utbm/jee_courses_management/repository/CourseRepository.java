package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Course;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CourseRepository implements Serializable {

    private EntityManager entityManager;

    public CourseRepository() {
        entityManager = EntityManagerFactory.getEntityManager();
    }

    /**
     * Get all courses from the entity {@link Course}
     *
     * @return A {List} containing all found courses. The returned List can be empty but it cannot be null.
     */
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        for (Object result : entityManager.createQuery("from Course").getResultList()) {
            if (result instanceof Course) {
                entityManager.detach(result);
                Course course = (Course) result;
                courses.add(course);
            }
        }

        return courses;
    }

    /**
     * Save a new {@link Course} in database.
     *
     * @param course The {@link Course} to save.
     */
    public void save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.merge(course);
        entityManager.getTransaction().commit();
    }
}