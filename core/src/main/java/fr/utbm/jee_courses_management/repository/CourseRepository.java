package fr.utbm.jee_courses_management.repository;


import fr.utbm.jee_courses_management.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("courses-management");
    EntityManager entityManager = null;

    /**
     * Get all courses from the entity {@link Course}
     *
     * @return A {List} containing all found courses. The returned List can be empty but it cannot be null.
     */
    public List<Course> getCourses() {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();

        List<Course> courses = new ArrayList<>();
        for (Object result : entityManager.createQuery("from Course").getResultList())
            try {
                entityManager.detach(result);
                courses.add((Course) result);
            } catch (ClassCastException exception) {
                System.out.println("The object " + result.toString() + " cannot be casted");
            }

        return courses;
    }

    /**
     * Save a new {@link Course} in database.
     *
     * @param course The {@link Course} to save.
     */
    public void save(Course course) {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(course);
        entityManager.getTransaction().commit();
    }
}
