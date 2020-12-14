package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.service.ClientService;
import fr.utbm.jee_courses_management.service.implementation.ClientServiceImpl;

import java.io.Serializable;

/**
 * Controller for verifications and operations on or about {@link Client}
 */
public class ClientController implements Serializable {

    /** A service which do operations. */
    private final ClientService service;

    /** (constructor)
     * Default constructor which initialize the service
     */
    public ClientController() {
        service = new ClientServiceImpl();
    }

    /**
     * Register a {@link Client} to a {@link fr.utbm.jee_courses_management.entity.CourseSession}.
     * The id of the {@link fr.utbm.jee_courses_management.entity.CourseSession} is stored in the {@link Client}.
     *
     * @param client The {@link Client} to register.
     * @return The registered {@link Client}. Will return null if the registration fails.
     */
    // TODO Test
    public Client registerClient(Client client) {
        if (client.getFirstname() == null || client.getFirstname().isBlank() ||
                client.getLastname() == null || client.getLastname().isBlank() ||
                client.getAdress() == null || client.getAdress().isBlank() ||
                client.getPhoneNumber() == null || client.getPhoneNumber().isBlank() ||
                client.getSession() == null)
            return null;

        return service.registerClient(client);
    }
}