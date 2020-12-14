package fr.utbm.jee_courses_management.service.implementation;

import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.repository.ClientRepository;
import fr.utbm.jee_courses_management.service.ClientService;

/**
 * An implementation of the service {@link ClientService}.
 */
public class ClientServiceImpl implements ClientService {

    /** A repository a access to the {@link Client} entity. */
    private final ClientRepository repository;

    /** (constructor)
     * Default constructor which initialize the repository.
     */
    public ClientServiceImpl() {
        repository = new ClientRepository();
    }

    /** @see ClientService#registerClient(Client) */
    // TODO Test
    @Override
    public Client registerClient(Client client) {
        return repository.register(client);
    }
}
