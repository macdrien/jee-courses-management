package fr.utbm.jee_courses_management.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter implements Serializable {

    /** A keyword which must be in the {@link fr.utbm.jee_courses_management.entity.Course} title */
    private String keyword;

    /** The minimum date to begin the session */
    private LocalDate startingDate;

    /** The minimum date to terminate the session */
    private LocalDate endingDate;

    /** The name of the city where the session have to take place */
    private String city;
}
