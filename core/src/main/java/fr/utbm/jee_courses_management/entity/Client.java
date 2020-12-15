package fr.utbm.jee_courses_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** A {@link Client} entity representing the table "Clients" in database. */
@Entity
@Table(name = "CLIENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    /** The id of the {@link Client} */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Integer id;

    /** The firstname of the {@link Client} */
    @Column(name = "firstname", nullable = false)
    private String firstname;

    /** The lastname of the {@link Client} */
    @Column(name = "lastname", nullable = false)
    private String lastname;

    /** The address of the {@link Client} */
    @Column(name = "address", nullable = false)
    private String address;

    /** The phone number of the {@link Client} */
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    /** The email of the {@link Client} */
    @Column(name = "email")
    private String email;

    /** The session on which the {@link Client} is registered */
    @JoinColumn(name = "id_course_session")
    @ManyToOne(fetch = FetchType.EAGER)
    private CourseSession session;
}
