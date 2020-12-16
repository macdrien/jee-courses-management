package fr.utbm.jee_courses_management.repository;

import fr.utbm.jee_courses_management.entity.Course;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Class containing methods accessing to the database on the {@link Course} entity. */
@AllArgsConstructor
public class CourseRepository implements Serializable {

    /** {@link EntityManager} to allow requests and operations. */
    private final EntityManager entityManager;

    /** Repository to access to operations on {@link fr.utbm.jee_courses_management.entity.CourseSession} */
    private final CourseSessionRepository courseSessionRepository;

    /** (constructor)
     * Default constructor to initialize the {@link EntityManager} and the {@link CourseSessionRepository}.
     */
    public CourseRepository() {
        entityManager = EntityManagerFactory.getEntityManager();
        courseSessionRepository = new CourseSessionRepository();
    }

    /**
     * Get all courses from the entity {@link Course}
     *
     * @return A {@link List} containing all found {@link Course}s. The returned List can be empty but it cannot be null.
     */
    public List<Course> getCourses(boolean loadSessions) {
        List<Course> courses = new ArrayList<>();
        entityManager.createQuery("from Course").getResultList().forEach(result -> {
            if (result instanceof Course) {
                entityManager.detach(result);
                Course course = (Course) result;

                if (loadSessions)
                    course.setSessions(courseSessionRepository.getCourseSessionsByCourseId(course.getId()));

                courses.add(course);
            }
        });

        return courses;
    }
}
