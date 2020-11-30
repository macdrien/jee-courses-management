package fr.utbm.jee_courses_management.util;

import fr.utbm.jee_courses_management.entity.Course;
import fr.utbm.jee_courses_management.entity.CourseSession;
import fr.utbm.jee_courses_management.entity.Location;
import fr.utbm.jee_courses_management.repository.EntityManagerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class DataSetup {

    public static void dataSetup() {
        /* Setup tests data */
        EntityManager entityManager = EntityManagerFactory.getEntityManager();

        Course course1 = new Course(),
                course2 = new Course(),
                course3 = new Course();
        course1.setTitle("LO54");
        course2.setTitle("AD50");
        course3.setTitle("SR50");

        Location location1 = new Location(),
                location2 = new Location(),
                location3 = new Location();
        location1.setCity("Belfort");
        location2.setCity("Mulhouse");
        location3.setCity("Montbéliard");

        entityManager.getTransaction().begin();
        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.persist(course3);
        entityManager.persist(location1);
        entityManager.persist(location2);
        entityManager.persist(location3);
        entityManager.getTransaction().commit();

        CourseSession session1 = new CourseSession(),
                session2 = new CourseSession(),
                session3 = new CourseSession();
        session1.setStartingDate(LocalDate.of(2020, 9, 1));
        session1.setEndingDate(LocalDate.of(2021, 2, 1));
        session1.setMaxStudents(50);
        session1.setCourse(course1);
        session1.setLocation(location1);

        session2.setStartingDate(LocalDate.of(2020, 7, 15));
        session2.setEndingDate(LocalDate.of(2021, 1, 23));
        session1.setCourse(course2);
        session1.setLocation(location1);

        session3.setStartingDate(LocalDate.of(2020, 4, 12));
        session3.setEndingDate(LocalDate.of(2020, 10, 10));
        session1.setMaxStudents(25);
        session1.setCourse(course2);
        session1.setLocation(location3);

        entityManager.getTransaction().begin();
        entityManager.persist(session1);
        entityManager.persist(session2);
        entityManager.persist(session3);
        entityManager.getTransaction().commit();
    }
}
