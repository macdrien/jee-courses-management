package fr.utbm.jee_courses_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/** A {@link Course} entity representing the table "Courses" in database. */
@Entity
@Table(name = "COURSES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    /** The id of the {@link Course} */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Integer id;

    /** The title of the {@link Course} */
    @Column(name = "title", nullable = false)
    private String title;

    /** A {@link List} of the {@link Course}'s sessions ({@link CourseSession}) */
    @Transient
    private List<CourseSession> sessions;
}
