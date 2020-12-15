package fr.utbm.jee_courses_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/** A {@link CourseSession} entity representing the table "Course_sessions" in database. */
@Entity
@Table(name = "COURSE_SESSIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSession {

    /** The id of the {@link CourseSession} */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course_session")
    private Integer id;

    /** The starting date of the {@link CourseSession} */
    @Column(name = "start_date", nullable = false)
    private LocalDate startingDate;

    /** The ending date of the {@link CourseSession} */
    @Column(name = "end_date", nullable = false)
    private LocalDate endingDate;

    /** The student limit of the {@link CourseSession} if there is one */
    @Column(name = "max_students")
    private Integer maxStudents;

    /** The course which is reprensented by the {@link CourseSession} */
    @JoinColumn(name = "id_course")
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    /** The location of the {@link CourseSession} */
    @JoinColumn(name = "id_location")
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    /** A {@link List} of the registered {@link Client} for the {@link CourseSession} */
    @Transient
    private List<Client> clients;

    /** The number of registered {@link Client} for the {@link CourseSession} */
    @Transient
    private Integer clientNumber;
}
