package fr.utbm.jee_courses_management.service;

import fr.utbm.jee_courses_management.entity.Client;

import java.io.Serializable;

/** Service which run operations on or about {@link Client}. */
public interface ClientService extends Serializable {

    /**
     * Register a {@link Client} to its selected {@link fr.utbm.jee_courses_management.entity.CourseSession}.
     *
     * @param client The {@link Client} to register. This object contains the session id too.
     * @return The registered {@link Client}. Null if an error occurred.
     */
    Client registerClient(Client client);
}
