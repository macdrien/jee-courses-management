package fr.utbm.jee_courses_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COURSE_SESSION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course_session")
    private Integer id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startingDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endingDate;

    @Column(name = "max_students")
    private Integer maxStudents;

    @JoinColumn(name = "id_course")
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @JoinColumn(name = "id_location")
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;
}
