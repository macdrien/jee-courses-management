package fr.utbm.jee_courses_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** A {@link Location} entity representing the table "Locations" in database. */
@Entity
@Table(name = "LOCATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    /** The id of the {@link Location} */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location")
    private Integer id;

    /** The city of the {@link Location} */
    @Column(name = "city", nullable = false)
    private String city;

    /** (constructor)
     * To initialize quickly and easily a new {@link Location}
     *
     * @param city The city of the new {@link Location}
     */
    public Location(String city) {
        this.city = city;
    }
}
