package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.repository.ClientRepository;
import fr.utbm.jee_courses_management.service.ClientService;
import fr.utbm.jee_courses_management.service.CourseSessionService;
import lombok.AllArgsConstructor;

/** An implementation of the service {@link ClientService}. */
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    /** A repository a access to the {@link Client} entity. */
    private final ClientRepository repository;

    /** Service for operations on {@link fr.utbm.jee_courses_management.entity.CourseSession} */
    private final CourseSessionService courseSessionService;

    /** (constructor)
     * Default constructor which initialize the {@link ClientRepository} and the necessary {@link CourseSessionService}.
     */
    public ClientServiceImpl() {
        repository = new ClientRepository();
        courseSessionService = new CourseSessionServiceImpl();
    }

    /** @see ClientService#registerClient(Client) */
    @Override
    public Client registerClient(Client client) {
        if (client.getFirstname() == null || client.getFirstname().isBlank() ||
                client.getLastname() == null || client.getLastname().isBlank() ||
                client.getAddress() == null || client.getAddress().isBlank() ||
                client.getPhoneNumber() == null || client.getPhoneNumber().isBlank() ||
                client.getSession() == null || client.getSession().getId() == null)
            return null;

        client.setSession(courseSessionService.getCourseSession(client.getSession().getId()));

        return client.getSession() == null ? null : repository.register(client);
    }
}
